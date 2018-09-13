package com.ogc.standard.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IEthTransactionBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IEthTransactionDAO;
import com.ogc.standard.domain.CtqEthTransaction;
import com.ogc.standard.domain.EthTransaction;
import com.ogc.standard.exception.BizException;

@Component
public class EthTransactionBOImpl extends PaginableBOImpl<EthTransaction>
        implements IEthTransactionBO {

    // private static Web3j web3j = Web3j
    // .build(new HttpService(PropertiesUtil.Config.ETH_URL));
    //
    @Autowired
    private IEthTransactionDAO ethTransactionDAO;

    @Override
    public int saveEthTransaction(CtqEthTransaction ctqEthTransaction) {
        int count = 0;
        if (ctqEthTransaction != null) {
            EthTransaction condition = new EthTransaction();
            condition.setHash(ctqEthTransaction.getHash());
            // 一条交易记录可能对应多条tokenEvent，如果数据库已经有了，则不插入
            if (ethTransactionDAO.selectTotalCount(condition) <= 0) {
                EthTransaction transaction = new EthTransaction();

                transaction.setHash(ctqEthTransaction.getHash());
                transaction.setNonce(ctqEthTransaction.getNonce());
                transaction.setBlockHash(ctqEthTransaction.getBlockHash());
                transaction.setBlockNumber(ctqEthTransaction.getBlockNumber());
                transaction.setBlockCreateDatetime(ctqEthTransaction
                    .getBlockCreateDatetime());

                transaction.setTransactionIndex(ctqEthTransaction
                    .getTransactionIndex());
                transaction.setFrom(ctqEthTransaction.getFrom());
                transaction.setTo(ctqEthTransaction.getTo());
                transaction.setValue(ctqEthTransaction.getValue());
                transaction
                    .setSyncDatetime(ctqEthTransaction.getSyncDatetime());

                transaction.setGasPrice(ctqEthTransaction.getGasPrice());
                transaction.setGasLimit(ctqEthTransaction.getGasLimit());
                transaction.setGasUsed(ctqEthTransaction.getGasUsed());
                transaction.setGasFee(ctqEthTransaction.getGasFee());
                transaction.setInput(ctqEthTransaction.getInput());

                transaction.setPublicKey(ctqEthTransaction.getPublicKey());
                transaction.setRaw(ctqEthTransaction.getRaw());
                transaction.setR(ctqEthTransaction.getR());
                transaction.setS(ctqEthTransaction.getS());

                count = ethTransactionDAO.insert(transaction);
            }
        }
        return count;
    }

    // @Override
    // public List<EthTransaction> queryEthTransactionList(
    // EthTransaction condition) {
    // return ethTransactionDAO.selectList(condition);
    // }
    //
    @Override
    public EthTransaction getEthTransaction(String hash) {
        EthTransaction data = null;
        if (StringUtils.isNotBlank(hash)) {
            EthTransaction condition = new EthTransaction();
            condition.setHash(hash);
            data = ethTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "以太坊交易记录不存在");
            }
        }
        return data;
    }
    //
    // @Override
    // public BigDecimal getGasPrice() {
    // BigDecimal price = null;
    // try {
    // price = new BigDecimal(
    // web3j.ethGasPrice().send().getGasPrice().toString());
    // } catch (IOException e) {
    // throw new BizException("xn0000", "以太坊gas价格获取异常");
    // }
    // return price;
    // }
    //
    // @Override
    // public String broadcast(String from, EthAddress fromSecret, String to,
    // BigDecimal value) {
    // String txHash = null;
    // try {
    // String fileDirPath = PropertiesUtil.Config.KEY_STORE_PATH;
    // File keystoreFile = new File(
    // fileDirPath + "/" + fromSecret.getKeystoreName());
    // if (!keystoreFile.exists()) {
    // keystoreFile.createNewFile();
    // FileWriter fw = null;
    // BufferedWriter bw = null;
    // try {
    // fw = new FileWriter(keystoreFile.getAbsoluteFile(), true); //
    // true表示可以追加新内容
    // // fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
    // bw = new BufferedWriter(fw);
    // bw.write(fromSecret.getKeystoreContent());
    // bw.close();
    // } catch (Exception e) {
    // throw new BizException("xn625000",
    // "keystore文件写入异常，原因" + e.getMessage());
    // }
    // }
    // // File keyStoreFileDir = new File(fileDirPath);
    // // File[] subFiles = keyStoreFileDir.listFiles();
    // // File keystoreFile = null;
    // // for (File file : subFiles) {
    // // if (file.isDirectory() != true) {
    // // // from: 0x244eb6078add0d58b2490ae53976d80f54a404ae
    // // if (file.getName().endsWith(from.substring(2))) {
    // // // 找到了该文件
    // // keystoreFile = file;
    // // break;
    // // }
    // // }
    // // }
    // // if (keystoreFile == null) {
    // // throw new BizException("xn6250000", "未找到keystore文件");
    // // }
    // //
    // Credentials credentials = WalletUtils
    // .loadCredentials(fromSecret.getPassword(), keystoreFile);
    // //
    // EthGetTransactionCount ethGetTransactionCount = web3j
    // .ethGetTransactionCount(from, DefaultBlockParameterName.LATEST)
    // .sendAsync().get();
    // //
    // BigInteger nonce = ethGetTransactionCount.getTransactionCount();
    //
    // // TODO 动态获取
    // BigInteger gasLimit = BigInteger.valueOf(21000);
    // BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
    //
    // // 本地签名的
    // RawTransaction rawTransaction = RawTransaction.createTransaction(
    // nonce, gasPrice, gasLimit, to, new BigInteger(value.toString()),
    // "");
    //
    // // 签名
    // byte[] signedMessage = TransactionEncoder
    // .signMessage(rawTransaction, credentials);
    // txHash = Numeric.toHexString(signedMessage);
    // EthSendTransaction ethSendTransaction = web3j
    // .ethSendRawTransaction(txHash).sendAsync().get();
    //
    // if (ethSendTransaction.getError() != null) {
    // // failure
    // }
    // txHash = ethSendTransaction.getTransactionHash();
    //
    // } catch (Exception e) {
    // throw new BizException("xn625000", "交易广播异常" + e.getMessage());
    // }
    // return txHash;
    // // success
    //
    // }
}
