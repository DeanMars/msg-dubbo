package com.msgtouch.server.service;

import com.msgtouch.common.service.MsgService;
import org.springframework.stereotype.Service;

/**
 * Created by Dean on 2016/10/9.
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {


    @Override
    public String getMsg() {
        return "MsgServiceImpl getMsg success";
    }


}
