package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.system.mapper.DictionaryMapper;
import com.common.system.entity.Dictionary;
import com.common.system.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * 字典服务实现类
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

}
