package com.ogc.standard.bo.impl;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.domain.EthXAddress;

@Component
public class EthXAddressBOImpl extends PaginableBOImpl<EthXAddress>
        implements IEthXAddressBO {

    // private static Logger logger = Logger.getLogger(EthXAddressBOImpl.class);
    //
    // private static Admin admin = EthClient.getClient();
    //
    // @Autowired
    // private IEthXAddressDAO ethAddressDAO;
    //
    // @Override
    // public String generateAddress(String userId, String accountNumber) {
    //
    // EthXAddress newAddress = EthClient.newAccount();
    //
    // // 落地地址信息
    // String status = null;
    // if (EAddressType.X.getCode().equals(type.getCode())) {
    // status = EXAddressStatus.NORMAL.getCode();
    // } else if (EAddressType.M.getCode().equals(type.getCode())) {
    // status = EMAddressStatus.NORMAL.getCode();
    // } else {
    // throw new BizException("不支持生成该类型的ETH地址");
    // }
    // this.saveEthXAddress(type, userId, accountNumber,
    // newAddress.getAddress(), newAddress.getPassword(), BigDecimal.ZERO,
    // status, newAddress.getKeystoreName(),
    // newAddress.getKeystoreContent());
    //
    // return newAddress.getAddress();
    // }
    //
    // // @Override
    // // public String generateAddress(EAddressType type, String userId,
    // // String accountNumber) {
    // // String address = null;
    // // String password = RandomUtil.generate8();
    // // try {
    // // NewAccountIdentifier newAccountIdentifier = selfAdmin
    // // .personalNewAccount(password).send();
    // // if (newAccountIdentifier != null) {
    // // address = newAccountIdentifier.getAccountId();
    // // }
    // // } catch (IOException e) {
    // // throw new BizException("xn625000", "以太坊账户创建失败，请检查节点是否正常！原因："
    // // + e.getMessage());
    // // }
    // // if (StringUtils.isBlank(address)) {
    // // throw new BizException("xn625000", "以太坊账户创建失败，请检查节点是否正常！");
    // // }
    // // logger.info("以太坊账户创建成功:" + address);
    // // try {
    // // // 获取keystore文件
    // // File keystoreFile = null;
    // // String fileDirPath = PropertiesUtil.Config.KEY_STORE_PATH;
    // // File keyStoreFileDir = new File(fileDirPath);
    // // File[] subFiles = keyStoreFileDir.listFiles();
    // //
    // // for (File file : subFiles) {
    // // if (file.isDirectory() != true) {
    // // // from: 0x244eb6078add0d58b2490ae53976d80f54a404ae
    // // if (file.getName().endsWith(address.substring(2))) {
    // // // 找到了该文件
    // // keystoreFile = file;
    // // break;
    // // }
    // // }
    // // }
    // //
    // // if (keystoreFile == null) {
    // // throw new BizException("xn6250000", "未找到keystore文件，请仔细检查本地节点配置");
    // // }
    // //
    // // FileInputStream fis = new FileInputStream(keystoreFile);
    // // byte[] buf = new byte[1024];
    // // StringBuffer sb = new StringBuffer();
    // // while ((fis.read(buf)) != -1) {
    // // sb.append(new String(buf));
    // // buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
    // // }
    // // String keystoreContent = new String(sb.toString().getBytes(),
    // // "utf-8");
    // // fis.close();
    // // // 落地地址信息
    // // String status = null;
    // // if (EAddressType.X.getCode().equals(type.getCode())) {
    // // status = EXAddressStatus.NORMAL.getCode();
    // // } else if (EAddressType.M.getCode().equals(type.getCode())) {
    // // status = EMAddressStatus.NORMAL.getCode();
    // // } else {
    // // throw new BizException("不支持生成该类型的ETH地址");
    // // }
    // // this.saveEthXAddress(type, userId, accountNumber, address, password,
    // // BigDecimal.ZERO, status, keystoreFile.getName(),
    // // keystoreContent);
    // // } catch (Exception e) {
    // // throw new BizException("xn6250000", "获取keystore文件异常，原因："
    // // + e.getMessage());
    // // }
    // //
    // // return address;
    // // }
    //
    // @Override
    // public String saveEthXAddress(EAddressType type, String userId,
    // String accountNumber, String address, String password,
    // BigDecimal balance, String status, String keystoreName,
    // String keystoreContent) {
    // String code = OrderNoGenerater.generate("ETH");
    // Date now = new Date();
    // EthXAddress data = new EthXAddress();
    // data.setCode(code);
    // data.setType(type.getCode());
    // data.setAddress(address);
    // data.setPassword(password);
    // data.setUserId(userId);
    // data.setAccountNumber(accountNumber);
    // data.setInitialBalance(getEthBalance(address));
    // data.setBalance(balance);
    // data.setStatus(status);
    // data.setCreateDatetime(now);
    // data.setUpdateDatetime(now);
    // data.setKeystoreName(keystoreName);
    // data.setKeystoreContent(keystoreContent);
    // ethAddressDAO.insert(data);
    // return code;
    // }
    //
    // @Override
    // public List<EthXAddress> queryEthXAddressList(EthXAddress condition) {
    // return ethAddressDAO.selectList(condition);
    // }
    //
    // @Override
    // public EthXAddress getWEthXAddressToday() {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(EAddressType.W.getCode());
    // condition.setStatus(EPersonalAddressStatus.NORMAL.getCode());
    // condition.setOrder("create_datetime", "desc");
    // List<EthXAddress> wList = ethAddressDAO.selectList(condition);
    // if (CollectionUtils.isEmpty(wList)) {
    // throw new BizException("xn625000", "未找到可用的归集地址");
    // }
    // return wList.get(0);
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(String code) {
    // EthXAddress data = null;
    // if (StringUtils.isNotBlank(code)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setCode(code);
    // data = ethAddressDAO.select(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "以太坊地址不存在");
    // }
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressSecret(String code) {
    // EthXAddress data = null;
    // if (StringUtils.isNotBlank(code)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setCode(code);
    // data = ethAddressDAO.selectSecret(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "以太坊地址不存在");
    // }
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(EAddressType type, String address) {
    // EthXAddress data = null;
    // EthXAddress condition = new EthXAddress();
    // condition.setType(type.getCode());
    // condition.setAddress(address);
    // List<EthXAddress> results = ethAddressDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // data = results.get(0);
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByUserId(String userId) {
    // EthXAddress data = null;
    // EthXAddress condition = new EthXAddress();
    // condition.setUserId(userId);
    // List<EthXAddress> results = ethAddressDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // data = results.get(0);
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByAccountNumber(String accountNumber) {
    // EthXAddress data = null;
    // EthXAddress condition = new EthXAddress();
    // condition.setAccountNumber(accountNumber);
    // List<EthXAddress> results = ethAddressDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // data = results.get(0);
    // }
    // return data;
    // }
    //
    // @Override
    // public BigDecimal getEthBalance(String address) {
    // try {
    // DefaultBlockParameter defaultBlockParameter =
    // DefaultBlockParameterName.LATEST;
    // EthGetBalance ethGetBalance = admin
    // .ethGetBalance(address, defaultBlockParameter).send();
    // if (ethGetBalance != null) {
    // return new BigDecimal(ethGetBalance.getBalance().toString());
    // } else {
    // throw new BizException("xn625000", "以太坊余额查询失败");
    // }
    // } catch (Exception e) {
    // throw new BizException("xn625000",
    // "以太坊余额查询异常，原因：" + e.getMessage());
    // }
    // }
    //
    // @Override
    // public int abandonAddress(EthXAddress ethAddress) {
    // int count = 0;
    // if (ethAddress != null) {
    // Date now = new Date();
    // ethAddress.setStatus(EWAddressStatus.INVALID.getCode());
    // ethAddress.setAbandonDatetime(now);
    // ethAddress.setUpdateDatetime(now);
    // ethAddressDAO.updateAbandon(ethAddress);
    // }
    // return count;
    // }
    //
    // @Override
    // public int refreshBalance(EthXAddress address) {
    // int count = 0;
    // if (address != null) {
    // address.setBalance(getEthBalance(address.getAddress()));
    // address.setUpdateDatetime(new Date());
    // ethAddressDAO.updateBalance(address);
    // }
    // return count;
    // }
    //
    // @Override
    // public int refreshStatus(EthXAddress address, String status) {
    // int count = 0;
    // if (address != null) {
    // address.setStatus(status);
    // address.setUpdateDatetime(new Date());
    // ethAddressDAO.updateStatus(address);
    // }
    // return count;
    // }
    //
    // @Override
    // public boolean isEthXAddressExist(String address) {
    // boolean flag = false;
    // if (StringUtils.isNotBlank(address)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setAddress(address);
    // if (ethAddressDAO.selectTotalCount(condition) > 0) {
    // flag = true;
    // }
    // }
    // return flag;
    // }
    //
    // @Override
    // public BigDecimal getTotalBalance(EAddressType type) {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(type.getCode());
    // return ethAddressDAO.selectTotalBalance(condition);
    // }
    //
    // @Override
    // public List<EthXAddress> queryManualCollectAddressPage(
    // BigDecimal balanceStart, int start, int limit) {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(EAddressType.X.getCode());
    // condition.setBalanceStart(balanceStart);
    // return ethAddressDAO.selectList(condition, start, limit);
    // }
    //
    // @Override
    // public String generateAddress(String userId, String accountNumber) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public String saveEthXAddress(String userId, String accountNumber,
    // String address, String password, BigDecimal balance, String status,
    // String keystoreName, String keystoreContent) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getWEthXAddressToday() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public int refreshBalance(EthXAddress address) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public int refreshStatus(EthXAddress address, String status) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByAddress(String address) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByUserId(String userId) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByAccountNumber(String accountNumber) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public boolean isEthXAddressExist(String address) {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressSecret(String code) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public List<EthXAddress> queryEthXAddressList(EthXAddress condition) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(String code) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public int abandonAddress(EthXAddress ethAddress) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public BigDecimal getTotalBalance() {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
