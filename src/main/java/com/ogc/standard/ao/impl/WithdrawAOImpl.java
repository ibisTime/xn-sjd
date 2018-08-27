package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.bitcoin.BitcoinOfflineRawTxBuilder;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcClient;
import com.cdkj.coin.wallet.bitcoin.BtcTransaction;
import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.OfflineTxInput;
import com.cdkj.coin.wallet.bitcoin.OfflineTxOutput;
import com.cdkj.coin.wallet.bitcoin.original.BTCOriginalTx;
import com.cdkj.coin.wallet.bitcoin.util.BtcBlockExplorer;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.IBtcTransactionBO;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.ICoinBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.IJourBO;
import com.cdkj.coin.wallet.bo.ITokenAddressBO;
import com.cdkj.coin.wallet.bo.ITokenTransactionBO;
import com.cdkj.coin.wallet.bo.IWTokenAddressBO;
import com.cdkj.coin.wallet.bo.IWanAddressBO;
import com.cdkj.coin.wallet.bo.IWanTransactionBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.AmountUtil;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.dto.res.XN802758Res;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.EBtcUtxoRefType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;
import com.cdkj.coin.wallet.enums.ECoinType;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EJourKind;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.ethereum.EthTransaction;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.token.TokenAddress;
import com.cdkj.coin.wallet.token.TokenClient;
import com.cdkj.coin.wallet.token.TokenTransaction;
import com.cdkj.coin.wallet.wanchain.WTokenAddress;
import com.cdkj.coin.wallet.wanchain.WanAddress;
import com.cdkj.coin.wallet.wanchain.WanClient;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    private static Logger logger = Logger.getLogger(WithdrawAOImpl.class);

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IWTokenAddressBO wtokenAddressBO;

    @Autowired
    private IWanTransactionBO wanTransactionBO;

    @Autowired
    private ITokenTransactionBO tokenTransactionBO;

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Autowired
    private IBtcUtxoBO btcUtxoBO;

    @Autowired
    private IBtcTransactionBO btcTransactionBO;

    @Autowired
    private BtcBlockExplorer btcBlockExplorer;

    @Autowired
    private ICoinBO coinBO;

    @Override
    @Transactional
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {

        Account dbAccount = accountBO.getAccount(accountNumber);
        Coin coin = coinBO.getCoin(dbAccount.getCurrency());

        BigDecimal fee = coin.getWithdrawFee();
        if (amount.compareTo(fee) == 0 || amount.compareTo(fee) == -1) {
            throw new BizException("xn000000", "提现金额需大于手续费");
        }

        // 取现地址格式校验以及是否被平台使用
        verifyPayCardNo(coin, payCardNo);

        // 账户可用余额是否充足
        if (dbAccount.getAmount().subtract(dbAccount.getFrozenAmount())
            .compareTo(amount) == -1) {
            throw new BizException("xn000000", "可用余额不足");
        }

        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);

        // 生成取现订单
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote);
        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, amount,
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getValue(), withdrawCode);

        return withdrawCode;

    }

    private void verifyPayCardNo(Coin coin, String payCardNo) {
        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
            if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
                if (!WalletUtils.isValidAddress(payCardNo)) {
                    throw new BizException("xn000000",
                        "提现地址不符合" + EOriginialCoin.ETH.getCode() + "规则，请仔细核对");
                }

                EthAddress condition = new EthAddress();
                condition.setAddress(payCardNo);
                if (ethAddressBO.getTotalCount(condition) > 0) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "提现地址已经在本平台被使用，请仔细核对！");
                }
            } else if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
                if (!BtcClient.verifyAddress(payCardNo)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "提现地址不符合" + EOriginialCoin.BTC.getCode() + "规则，请仔细核对");
                }

                BtcAddress condition = new BtcAddress();
                condition.setAddress(payCardNo);
                if (btcAddressBO.getTotalCount(condition) > 0) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "提现地址已经在本平台被使用，请仔细核对！");
                }
            }
        } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
            if (!WalletUtils.isValidAddress(payCardNo)) {
                throw new BizException("xn000000",
                    "提现地址不符合" + EOriginialCoin.ETH.getCode() + "规则，请仔细核对");
            }

            TokenAddress condition = new TokenAddress();
            condition.setAddress(payCardNo);
            if (tokenAddressBO.getTotalCount(condition) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址已经在本平台被使用，请仔细核对！");
            }
        } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {
            if (!WalletUtils.isValidAddress(payCardNo)) {
                throw new BizException("xn000000",
                    "提现地址不符合" + EOriginialCoin.WAN.getCode() + "规则，请仔细核对");
            }

            WTokenAddress condition = new WTokenAddress();
            condition.setAddress(payCardNo);
            if (wtokenAddressBO.getTotalCount(condition) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址已经在本平台被使用，请仔细核对！");
            }
        }

    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待审批状态，无法审批");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    @Override
    @Transactional
    public void broadcast(String code, String mAddressCode,
            String approveUser) {
        // 取现记录验证
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode()
            .equals(withdraw.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前取现记录不处于待广播状态");
        }

        Account account = accountBO.getAccount(withdraw.getAccountNumber());
        Coin coin = coinBO.getCoin(account.getCurrency());

        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
            if (EOriginialCoin.ETH.getCode().equals(account.getCurrency())) {
                if (StringUtils.isBlank(mAddressCode)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "散取地址不能为空");
                }
                doEthBroadcast(withdraw, mAddressCode, approveUser);
            } else if (EOriginialCoin.WAN.getCode()
                .equals(account.getCurrency())) {
                if (StringUtils.isBlank(mAddressCode)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "散取地址不能为空");
                }
                doWanBroadcast(withdraw, mAddressCode, approveUser);
            } else if (EOriginialCoin.BTC.getCode()
                .equals(account.getCurrency())) {
                doBtcBroadcast(withdraw, approveUser);
            }
        } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
            if (StringUtils.isBlank(mAddressCode)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "散取地址不能为空");
            }
            doTokenBroadcast(coin, withdraw, mAddressCode, approveUser);
        }

    }

    private void doTokenBroadcast(Coin coin, Withdraw withdraw,
            String mAddressCode, String approveUser) {
        // 获取今日散取地址
        TokenAddress mEthAddress = tokenAddressBO.getTokenAddress(mAddressCode);
        if (!EAddressType.M.getCode().endsWith(mEthAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "无效的ETH地址，只有散取地址才能进行取现广播！");
        }
        if (EMAddressStatus.IN_USE.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址正在广播使用，请稍后再试！");
        }
        if (EMAddressStatus.INVALID.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址已被弃用！");
        }

        String address = mEthAddress.getAddress();
        TokenAddress secret = tokenAddressBO
            .getTokenAddressSecret(mEthAddress.getCode());

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());

        // 查询散取地址token余额
        BigDecimal tokenBalance = tokenAddressBO.getTokenBalance(address,
            coin.getContractAddress());
        logger.info("地址" + address + coin.getSymbol() + "余额："
                + tokenBalance.toString());
        if (tokenBalance.compareTo(realAmount) < 0) {
            throw new BizException("xn625000",
                "地址" + address + coin.getSymbol() + "余额不足以支付提现金额！");
        }

        // 预估矿工费用
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(210000);
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 查询散取地址余额
        BigDecimal balance = ethAddressBO.getEthBalance(address);
        logger.info("地址" + address + "余额：" + balance.toString());
        if (balance.compareTo(txFee) < 0) {
            throw new BizException("xn625000",
                "散取地址" + address + "ETH余额不足以支付提现矿工费！");
        }

        // 广播
        if (!WalletUtils.isValidAddress(withdraw.getPayCardNo())) {
            throw new BizException("xn625000",
                "无效的取现地址：" + withdraw.getPayCardInfo());
        }
        String txHash = TokenClient.transfer(secret, withdraw.getPayCardNo(),
            realAmount, coin.getContractAddress());
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "提现广播失败");
        }
        logger.info("广播成功：交易hash=" + txHash);
        withdrawBO.broadcastOrder(withdraw, txHash, approveUser);

        // 修改取现地址状态为广播中
        tokenAddressBO.refreshStatus(mEthAddress,
            EMAddressStatus.IN_USE.getCode());
    }

    // btc广播逻辑：
    // 1、预估矿工费得到取现总金额(加找零算法),判断矿工费是否足够
    // 2、签名
    // 3、调用接口广播
    private void doBtcBroadcast(Withdraw withdraw, String approveUser) {
        // 实际到账金额=取现金额-取现手续费
        BigDecimal utxoCount = withdraw.getAmount().subtract(withdraw.getFee());
        // 获取散取地址的UTXO总额，判断是否足够提现
        BigDecimal enableCount = btcUtxoBO
            .getTotalEnableUTXOCount(EAddressType.M);
        // 相等或小于都应该是提现不成功，后续广播还要算上实际矿工费(大于也不一定成功，因为矿工费不能太小，容易交易失败)
        if (enableCount.compareTo(utxoCount) <= 0) {
            throw new BizException("xn0000", "散取地址总额不足，请及时定存!");
        }

        // 足够提现，降序遍历可使用的M类地址UTXO，组装Input
        BitcoinOfflineRawTxBuilder rawTxBuilder = new BitcoinOfflineRawTxBuilder();
        List<BtcUtxo> inputBtcUtxoList = buildRawTx(utxoCount,
            withdraw.getPayCardNo(), rawTxBuilder);
        // 广播
        broadcastBtcTx(withdraw, approveUser, rawTxBuilder, inputBtcUtxoList);
    }

    private List<BtcUtxo> buildRawTx(BigDecimal utxoCount,
            String withdrawAddress, BitcoinOfflineRawTxBuilder rawTxBuilder) {
        List<BtcUtxo> inputBtcUtxoList = new ArrayList<BtcUtxo>();

        BigDecimal shouldWithdrawCount = BigDecimal.ZERO;// 遍历汇总出最少条数的UTXO总额，这个总额=实际取现总额+矿工费+找零金额
        int preFee = 0;// 矿工费
        BigDecimal realUtxoCount = BigDecimal.ZERO;// 实际需要UTXO = 取现UXTO+矿工费

        int start = 1;
        int limit = 100;
        boolean enoughFlag = false;// 是否足够标志
        while (true) {
            List<BtcUtxo> list = btcUtxoBO.queryEnableUtxoList(start, limit,
                EAddressType.M);
            if (CollectionUtils.isNotEmpty(list)) {
                for (BtcUtxo utxo : list) {
                    String txid = utxo.getTxid();
                    Integer vout = utxo.getVout();
                    shouldWithdrawCount = shouldWithdrawCount
                        .add(utxo.getCount());
                    BtcAddress btcAddress = btcAddressBO
                        .getBtcAddress(EAddressType.M, utxo.getAddress());
                    // 构造签名交易，输入
                    OfflineTxInput offlineTxInput = new OfflineTxInput(txid,
                        vout, utxo.getScriptPubKey(),
                        btcAddress.getPrivatekey());
                    rawTxBuilder.in(offlineTxInput);
                    inputBtcUtxoList.add(utxo);
                    preFee = calMinerFee(inputBtcUtxoList.size(), 1);// 交易输出，默认有找零
                    realUtxoCount = utxoCount.add(BigDecimal.valueOf(preFee));

                    BigDecimal backCount = shouldWithdrawCount
                        .subtract(realUtxoCount);
                    // 需要找零
                    if (backCount.compareTo(BigDecimal.ZERO) > 0) {
                        preFee = calMinerFee(inputBtcUtxoList.size(), 2);
                        realUtxoCount = utxoCount
                            .add(BigDecimal.valueOf(preFee));
                        if (shouldWithdrawCount.compareTo(realUtxoCount) < 0) {// 增加找零输出，原有UTXO不够，再增加
                            continue;
                        } else {
                            enoughFlag = true;
                            break;
                        }
                        // 无需找零
                    } else if (backCount.compareTo(BigDecimal.ZERO) == 0) {
                        enoughFlag = true;
                        break;
                    }
                }
            } else {
                break;
            }

            // UTXO已足够，跳出循环
            if (enoughFlag) {
                break;
            }

            start++;// 不够再遍历
        }
        if (!enoughFlag) {
            throw new BizException("xn0000", "提现账户余额不足");
        }

        // 构造输出一，实际到账的金额
        OfflineTxOutput offlineTxOutput = new OfflineTxOutput(withdrawAddress,
            AmountUtil.fromBtc(utxoCount));
        rawTxBuilder.out(offlineTxOutput);

        // 计算需要的找零, 现在是随机找零到一个散取地址作为提现地址
        BigDecimal backCount = shouldWithdrawCount.subtract(utxoCount)
            .subtract(BigDecimal.valueOf(preFee));
        if (backCount.compareTo(BigDecimal.ZERO) > 0) {
            String backAddress = inputBtcUtxoList.get(0).getAddress();
            OfflineTxOutput backOutput = new OfflineTxOutput(backAddress,
                AmountUtil.fromBtc(backCount));
            rawTxBuilder.out(backOutput);
        }
        return inputBtcUtxoList;
    }

    private void broadcastBtcTx(Withdraw withdraw, String approveUser,
            BitcoinOfflineRawTxBuilder rawTxBuilder,
            List<BtcUtxo> inputBtcUtxoList) {
        try {
            String signResult = rawTxBuilder.offlineSign();
            // 广播
            String trueTxid = btcBlockExplorer.broadcastRawTx(signResult);
            if (trueTxid != null) {
                if (CollectionUtils.isNotEmpty(inputBtcUtxoList)) {
                    for (BtcUtxo data : inputBtcUtxoList) {
                        btcUtxoBO.refreshBroadcast(data, EBtcUtxoStatus.USING,
                            EBtcUtxoRefType.WITHDRAW, withdraw.getCode());

                    }
                }
                logger.info("广播成功：交易hash=" + trueTxid);
                withdrawBO.broadcastOrder(withdraw, trueTxid, approveUser);
            } else {
                throw new BizException(EBizErrorCode.UTXO_WITHDRAW_ERROR);
            }
        } catch (Exception e) {
            throw new BizException("-100", e.getMessage());
        }
    }

    /**
     * 计算本次交易矿工费
     * @param inCount
     * @param outCount
     * @return 
     * @create: 2018年2月22日 下午5:28:04 xieyj
     * @history:
     */
    private int calMinerFee(int inCount, int outCount) {
        // 组装Output，设置找零账户
        // 如何估算手续费，先预先给一个size,然后拿这个size进行签名
        // 对签名的数据进行解码，拿到真实大小，然后进行矿工费的修正
        int preSize = BitcoinOfflineRawTxBuilder.calculateSize(inCount,
            outCount);
        int feePerByte = btcBlockExplorer.getFee();

        // 计算出手续费
        int preFee = preSize * feePerByte;
        return preFee;
    }

    private void doEthBroadcast(Withdraw withdraw, String mAddressCode,
            String approveUser) {
        // 获取今日散取地址
        EthAddress mEthAddress = ethAddressBO.getEthAddress(mAddressCode);
        if (!EAddressType.M.getCode().endsWith(mEthAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "无效的ETH地址，只有散取地址才能进行取现广播！");
        }
        if (EMAddressStatus.IN_USE.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址正在广播使用，请稍后再试！");
        }
        if (EMAddressStatus.INVALID.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址已被弃用！");
        }

        String address = mEthAddress.getAddress();
        EthAddress secret = ethAddressBO
            .getEthAddressSecret(mEthAddress.getCode());

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());

        // 预估矿工费用
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 查询散取地址余额
        BigDecimal balance = ethAddressBO.getEthBalance(address);
        logger.info("地址" + address + "余额：" + balance.toString());
        if (balance.compareTo(realAmount.add(txFee)) < 0) {
            throw new BizException("xn625000",
                "散取地址" + address + "余额不足以支付提现金额和矿工费！");
        }
        // 广播
        if (!WalletUtils.isValidAddress(withdraw.getPayCardNo())) {
            throw new BizException("xn625000",
                "无效的取现地址：" + withdraw.getPayCardInfo());
        }
        String txHash = ethTransactionBO.broadcast(address, secret,
            withdraw.getPayCardNo(), realAmount);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "交易签名失败，请仔细检查散取地址是否符合提现要求");
        }
        logger.info("广播成功：交易hash=" + txHash);
        withdrawBO.broadcastOrder(withdraw, txHash, approveUser);

        // 修改取现地址状态为广播中
        ethAddressBO.refreshStatus(mEthAddress,
            EMAddressStatus.IN_USE.getCode());

    }

    private void doWanBroadcast(Withdraw withdraw, String mAddressCode,
            String approveUser) {
        // 获取今日散取地址
        WanAddress mWanAddress = wanAddressBO.getWanAddress(mAddressCode);
        if (!EAddressType.M.getCode().endsWith(mWanAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "无效的WAN地址，只有散取地址才能进行取现广播！");
        }
        if (EMAddressStatus.IN_USE.getCode().equals(mWanAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址正在广播使用，请稍后再试！");
        }
        if (EMAddressStatus.INVALID.getCode().equals(mWanAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该散取地址已被弃用！");
        }

        String address = mWanAddress.getAddress();
        WanAddress secret = wanAddressBO
            .getWanAddressSecret(mWanAddress.getCode());

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());

        // 预估矿工费用
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 查询散取地址余额
        BigDecimal balance = wanAddressBO.getWanBalance(address);
        logger.info("地址" + address + "余额：" + balance.toString());
        if (balance.compareTo(realAmount.add(txFee)) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "散取地址" + address + "余额不足以支付提现金额和矿工费！");
        }
        // 广播
        if (!WalletUtils.isValidAddress(withdraw.getPayCardNo())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "无效的取现地址：" + withdraw.getPayCardInfo());
        }
        // String txHash = wanTransactionBO.broadcast(address, secret,
        // withdraw.getPayCardNo(), realAmount);
        String txHash = WanClient.transfer(secret, withdraw.getPayCardNo(),
            realAmount);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易签名失败，请仔细检查散取地址是否符合提现要求");
        }
        logger.info("广播成功：交易hash=" + txHash);
        withdrawBO.broadcastOrder(withdraw, txHash, approveUser);

        // 修改取现地址状态为广播中
        wanAddressBO.refreshStatus(mWanAddress,
            EMAddressStatus.IN_USE.getCode());

    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES, approveUser,
            approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());

    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode, payCode, BigDecimal.ZERO);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(), "取现失败退回", data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode) {
        // withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
        // payCode, payCode, BigDecimal.ZERO);
        // Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // // 先解冻，然后扣减余额
        // accountBO.unfrozenAmount(dbAccount, data.getAmount(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getCode());
        // accountBO.changeAmount(dbAccount.getAccountNumber(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getAmount());
        // Account account = accountBO.getAccount(data.getAccountNumber());
        // if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
        // // 冷钱包减钱
        // accountBO.changeAmount(
        // ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeCold.AJ_PAY.getCode(), "ETH取现", data.getAmount()
        // .negate());
        // }
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);
        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code, String systemCode) {
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        return withdraw;
    }

    // /**
    // * 取现申请检查，验证参数，返回手续费
    // * @param accountType
    // * @param amount
    // * @param systemCode
    // * @param companyCode
    // * @return
    // * @create: 2017年5月17日 上午7:53:01 xieyj
    // * @history:
    // */
    // private BigDecimal doGetFee(String accountType, BigDecimal amount,
    // String systemCode, String companyCode) {
    // Map<String, String> argsMap = sysConfigBO.getConfigsMap(systemCode,
    // companyCode);
    // String qxfl = null;
    // if (EAccountType.Customer.getCode().equals(accountType)) {
    // qxfl = SysConstants.CUSERQXFL;
    // } else {// 暂定其他账户类型不收手续费
    // return BigDecimal.ZERO;
    // }
    // // 取现单笔最大金额
    // String qxDbzdjeValue = argsMap.get(SysConstants.QXDBZDJE);
    // if (StringUtils.isNotBlank(qxDbzdjeValue)) {
    // BigDecimal qxDbzdje = BigDecimal.valueOf(Double
    // .valueOf(qxDbzdjeValue));
    // if (amount.compareTo(qxDbzdje) == 1) {
    // throw new BizException("xn000000", "取现单笔最大金额不能超过"
    // + qxDbzdjeValue + "元。");
    // }
    // }
    // String feeRateValue = argsMap.get(qxfl);
    // Double feeRate = 0D;
    // if (StringUtils.isNotBlank(feeRateValue)) {
    // feeRate = Double.valueOf(feeRateValue);
    // }
    // return AmountUtil.mul(amount, feeRate);
    // }

    @Override
    public XN802758Res getWithdrawCheckInfo(String code) {

        XN802758Res res = new XN802758Res();

        // 取现订单详情
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        Account account = accountBO.getAccount(withdraw.getAccountNumber());

        // 取现对应流水记录
        Jour jour = new Jour();
        jour.setRefNo(withdraw.getCode());
        jour.setKind(EJourKind.BALANCE.getCode());
        List<Jour> jourList = jourBO.queryJourList(jour);

        Coin coin = coinBO.getCoin(withdraw.getCurrency());
        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {

            if (EOriginialCoin.ETH.getCode().equals(account.getCurrency())) {
                // ETH取现对应广播记录
                EthTransaction ethTransaction = new EthTransaction();
                ethTransaction.setRefNo(withdraw.getCode());
                List<EthTransaction> resultList = ethTransactionBO
                    .queryEthTransactionList(ethTransaction);
                res.setEthTransList(resultList);
            } else if (EOriginialCoin.BTC.getCode()
                .equals(account.getCurrency())) {
                // BTC取现对应广播记录
                BtcTransaction btcTransaction = new BtcTransaction();
                btcTransaction.setRefNo(withdraw.getCode());
                List<BtcTransaction> resultList = btcTransactionBO
                    .queryBtcTransactionList(btcTransaction);
                res.setBtcTransList(resultList);
            }
        } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
            if (StringUtils.isNotBlank(withdraw.getChannelOrder())) {
                // 取现对应广播记录
                TokenTransaction condition = new TokenTransaction();
                condition.setHash(withdraw.getChannelOrder());
                List<TokenTransaction> resultList1 = tokenTransactionBO
                    .queryTokenTransactionList(condition);
                res.setTokenTransList(resultList1);
            }
        }

        res.setWithdraw(withdraw);
        res.setJourList(jourList);

        return res;
    }

    @Override
    public BigDecimal getTotalWithdraw(String currency) {
        return withdrawBO.getTotalWithdraw(currency);
    }

    public void doCheckBroadcastStatus() {
        while (true) {
            int start = 1;
            int limit = 10;
            Withdraw condition = new Withdraw();
            condition.setStatus(EWithdrawStatus.Broadcast.getCode());
            Paginable<Withdraw> withdraws = withdrawBO.getPaginable(start,
                limit, condition);
            if (CollectionUtils.isEmpty(withdraws.getList())) {
                break;
            }
            for (Withdraw withdraw : withdraws.getList()) {
                if (EOriginialCoin.BTC.getCode()
                    .equals(withdraw.getCurrency())) {// 交易记录消失，广播不成功处理
                    BTCOriginalTx btcTx = btcBlockExplorer
                        .queryTxHash(withdraw.getChannelOrder());
                    // 交易记录消失，广播不成功处理
                    if (null == btcTx) {
                        // utxo 改成可使用
                        List<BtcUtxo> list = btcUtxoBO
                            .queryBtcUtxoList(withdraw.getCode());
                        for (BtcUtxo btcUtxo : list) {
                            btcUtxoBO.refreshStatus(btcUtxo,
                                EBtcUtxoStatus.ENABLE);
                        }
                        payOrderNO(withdraw, "system", "广播失败,交易取消", "");
                    }
                }
                start++;
            }
        }
    }
}
