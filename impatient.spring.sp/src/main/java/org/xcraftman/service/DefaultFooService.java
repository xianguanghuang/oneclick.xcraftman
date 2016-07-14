package org.xcraftman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xcraftman.dao.TestMapper;

/**
 * Created by Administrator on 2016/7/14.
 */

@Component
public class DefaultFooService {

    @Autowired
    private TestMapper testMapper;

    public void setTestMapper(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public void updateTestColumn1(){
        this.testMapper.updateTest1Column();
    }
}
