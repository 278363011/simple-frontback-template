package com.codebaobao.service.impl;

import com.codebaobao.model.Student;
import com.codebaobao.mapper.StudentMapper;
import com.codebaobao.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
