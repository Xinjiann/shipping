package com.shipping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shipping.entity.Record;
import com.shipping.mapper.RecordMapper;
import com.shipping.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}
