package com.wangcheng.zeus.demo.web.control;

import com.fasterxml.jackson.annotation.JsonView;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.demo.domain.User;
import com.wangcheng.zeus.demo.service.UserManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : evan
 * @Date: 2018/9/13 21:22
 * @Description:
 */
@RestController
@RequestMapping(value = "/users")
@Api(description = "用户管理")
public class UserControl {

    @Autowired
    private UserManager userManager;

    @GetMapping
    @JsonView(value = ResponseModel.SimpleInfo.class)
    @ApiOperation("查询所有用户")
    public ResponseModel<List<User>> findAll(){
        return ResponseModel.SUCCESS(userManager.findAll());
    }

    @GetMapping(value = "/{id}")
    @JsonView(value = ResponseModel.SimpleDetailInfo.class)
    @ApiOperation("通过用id查询用户")
    public ResponseModel<User> find(@PathVariable Long id){
        return ResponseModel.SUCCESS(userManager.find(id));
    }

    @PostMapping
    @ApiOperation("新增用户")
    public ResponseModel<Boolean> add(@RequestBody User user){
        return ResponseModel.SUCCESS(userManager.add(user));
    }
    @PutMapping
    @ApiOperation("更新用户")
    public ResponseModel<Boolean> update(User user){
        return  ResponseModel.SUCCESS(userManager.update(user));
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public ResponseModel<Boolean> delete(@PathVariable Long id){
        return ResponseModel.SUCCESS(userManager.delete(id));
    }

}
