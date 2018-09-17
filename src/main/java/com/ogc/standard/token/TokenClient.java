/**
 * @Title TokenClient.java 
 * @Package com.cdkj.coin.wallet.contract 
 * @Description 
 * @author xieyj  
 * @date 2018年3月7日 下午8:54:24 
 * @version V1.0   
 */
package com.ogc.standard.token;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.RawTransactionManager;
import com.ogc.standard.token.OrangeCoinToken.TransferEventResponse;

import com.ogc.standard.common.PropertiesUtil;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: xieyj 
 * @since: 2018年3月7日 下午8:54:24 
 * @history:
 */
public class TokenClient {

    private static String TOKEN_URL = PropertiesUtil.Config.TOKEN_URL;

    private TokenClient() {
    }

    private volatile static Web3j web3j;

    public static Web3j getClient() {
        if (web3j == null) {
            synchronized (TokenClient.class) {
                if (web3j == null) {
                    web3j = Web3j.build(new HttpService(TOKEN_URL));
                }
            }
        }
        return web3j;
    }

    public static BigDecimal getGasPrice() {
        BigDecimal price = null;
        try {
            price = new BigDecimal(getClient().ethGasPrice().send()
                .getGasPrice().toString());
        } catch (IOException e) {
            throw new BizException("xn0000", "以太坊gas价格获取异常");
        }
        return price;
    }

    public static BigDecimal getEthBalance(String address) {
        try {
            DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;
            EthGetBalance ethGetBalance = getClient().ethGetBalance(address,
                defaultBlockParameter).send();
            if (ethGetBalance != null) {
                return new BigDecimal(ethGetBalance.getBalance().toString());
            } else {
                throw new BizException("xn625000", "以太坊余额查询失败");
            }
        } catch (Exception e) {
            throw new BizException("xn625000", "以太坊余额查询异常，原因：" + e.getMessage());
        }
    }

    // 查询余额
    public static BigDecimal getBalance(String address, String contractAddress) {
        BigDecimal balance = BigDecimal.ZERO;

        Function function = new Function("balanceOf",
            Arrays.<Type> asList(new org.web3j.abi.datatypes.Address(address)),
            Arrays.<TypeReference<?>> asList(new TypeReference<Uint256>() {
            }));
        String encodedFunction = FunctionEncoder.encode(function);

        EthCall ethCall;
        try {
            ethCall = getClient().ethCall(
                Transaction.createEthCallTransaction(address, contractAddress,
                    encodedFunction), DefaultBlockParameterName.LATEST).send();
            String value = ethCall.getValue();

            List<Type> values = FunctionReturnDecoder.decode(value,
                function.getOutputParameters());

            if (!values.isEmpty()) {
                balance = new BigDecimal(values.get(0).getValue().toString());
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "查询发送错误");
            }

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "以太坊智能合约查询余额失败，原因：" + e.getMessage());

        }

        return balance;
    }

    public static void main(String[] args) {
        // System.out
        // .println(getBalance("0x4ccdDE34dA3D55c1372c8D239d6633BC166b9665",
        // "0xb72614c410907BE6Ac7999133F84359b706C0187"));
        try {
            System.out.println(getClient()
                .ethGetCode("0x2aA6e4dA6F01b51041d6dC1685d365C8eaDee8Bc",
                    DefaultBlockParameterName.LATEST).send().getResult());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 转账
    public static String transfer(String fromAddress, String keystoreName,
            String keystorePwd, String keystoreContent, String toAddress,
            BigDecimal value, String contractAddress) {
        String hash = null;
        try {

            Credentials credentials = getCredentials(keystoreName,
                keystoreContent, keystorePwd);

            BigInteger gasLimit = BigInteger.valueOf(210000);
            BigInteger gasPrice = getClient().ethGasPrice().send()
                .getGasPrice();

            // 合约方法创建并encode
            Function function = new Function("transfer", Arrays.<Type> asList(
                new org.web3j.abi.datatypes.Address(toAddress),
                new org.web3j.abi.datatypes.generated.Uint256(new BigInteger(
                    value.toString()))),
                Collections.<TypeReference<?>> emptyList());
            String encodedFunction = FunctionEncoder.encode(function);

            // 获取发送地址当前交易数量
            EthGetTransactionCount ethGetTransactionCount = getClient()
                .ethGetTransactionCount(fromAddress,
                    DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // 未签名的交易
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, gasPrice, gasLimit, contractAddress, BigInteger.ZERO,
                encodedFunction);

            // 签名并发送
            RawTransactionManager rawTransactionManager = new RawTransactionManager(
                getClient(), credentials);
            EthSendTransaction ethSendTransaction = rawTransactionManager
                .signAndSend(rawTransaction);
            if (ethSendTransaction.getError() != null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    ethSendTransaction.getError().getMessage());
            }
            hash = ethSendTransaction.getTransactionHash();

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "智能合约token交易失败，原因：" + e.getMessage());
        }
        return hash;
    }

    private static Credentials getCredentials(String keystoreName,
            String keystoreContent, String password) {
        String fileDirPath = PropertiesUtil.Config.KEY_STORE_PATH;
        File keystoreFile = new File(fileDirPath + "/" + keystoreName);
        if (!keystoreFile.exists()) {
            try {
                keystoreFile.createNewFile();
                FileWriter fw = null;
                BufferedWriter bw = null;
                fw = new FileWriter(keystoreFile.getAbsoluteFile(), true); // true表示可以追加新内容
                // fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
                bw = new BufferedWriter(fw);
                bw.write(keystoreContent);
                bw.close();
            } catch (Exception e) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "keystore文件写入异常，原因" + e.getMessage());
            }
        }
        Credentials credentials;
        try {
            credentials = WalletUtils.loadCredentials(password, keystoreFile);
            return credentials;
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "创建以太坊credentials失败，原因" + e.getMessage());
        }
    }

    public static List<TransferEventResponse> loadTransferEvents(
            TransactionReceipt transactionReceipt) {

        List<OrangeCoinToken.TransferEventResponse> transferEventList = null;

        OrangeCoinToken contract = loadHolderContract(transactionReceipt
            .getTo());
        try {
            transferEventList = contract.getTransferEvents(transactionReceipt);
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "智能合约解析交易事件失败，原因：" + e.getMessage());
        }
        return transferEventList;

    }

    private volatile static OrangeCoinToken orangeCoinToken;

    // 加载合约
    public static OrangeCoinToken loadHolderContract(String contractAddress) {
        try {

            ClientTransactionManager transactionManager = new ClientTransactionManager(
                web3j, contractAddress);

            BigInteger gasLimit = BigInteger.valueOf(210000);
            BigInteger gasPrice = getClient().ethGasPrice().send()
                .getGasPrice();
            synchronized (TokenClient.class) {
                if (orangeCoinToken == null) {
                    orangeCoinToken = OrangeCoinToken.load(contractAddress,
                        getClient(), transactionManager, gasPrice, gasLimit);
                }
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "智能合约初始化失败,原因是" + e.getMessage());
        }
        return orangeCoinToken;
    }
}
