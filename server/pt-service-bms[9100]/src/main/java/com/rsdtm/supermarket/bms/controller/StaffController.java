package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.StaffControllerApi;
import com.rsdtm.supermarket.bms.service.StaffService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsStaff;
import com.rsdtm.supermarket.mbg.query.StaffParamVo;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("staff")
public class StaffController implements StaffControllerApi {
    @Autowired
    StaffService staffService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addStaff(
            @RequestBody BmsStaff bmsStaff) {
        int result = staffService.addStaff(bmsStaff);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteStaffById(
            @RequestParam(required = true) Integer id) {
        int result = staffService.deleteStaffById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateStaff(
             BmsStaff bmsStaff) {
        int result = staffService.updateStaff(bmsStaff);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<StaffVo> queryStaffById(
            @RequestParam(required = true) Integer id) {
        StaffVo result = staffService.queryStaffById(id);
        if (result == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<StaffVo>> queryAllStaffByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            StaffParamVo paramVo) {
        PageResult<StaffVo> result = staffService.queryAllStaffByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }
}
