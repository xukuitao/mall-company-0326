package cn.exrick.common.service;


import cn.exrick.common.pojo.TbAddress;

import java.util.List;

public interface AddressService {

    List<TbAddress> getAddressList(Long userId);

    TbAddress getAddress(Long addressId);

    int addAddress(TbAddress tbAddress);

    int updateAddress(TbAddress tbAddress);

    int delAddress(TbAddress tbAddress);
}
