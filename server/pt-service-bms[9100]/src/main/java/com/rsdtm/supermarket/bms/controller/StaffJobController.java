package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.StaffJobControllerApi;
import com.rsdtm.supermarket.bms.service.StaffJobService;
import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import com.rsdtm.supermarket.mbg.vo.BmsStaffJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staffJob")
public class StaffJobController implements StaffJobControllerApi {

    @Autowired
    StaffJobService staffJobService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addStaffJob(
            @RequestBody BmsStaffJob bmsStaffJob) {
        int result = staffJobService.addStaffJob(bmsStaffJob);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteStaffJobById(
            @RequestParam(required = true) Integer id) {
        int result = staffJobService.deleteStaffJobById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateStaffJob(
            BmsStaffJob bmsStaffJob) {
        int result = staffJobService.updateStaffJob(bmsStaffJob);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsStaffJobVo> queryStaffJobById(
            @RequestParam(required = true) Integer id) {
        BmsStaffJobVo result =  staffJobService.queryStaffJobById(id);

        return null;
    }

    @Override
    @GetMapping("all")
    public ResponseEntity<List<BmsStaffJobVo>> queryAllStaffJob() {
        List<BmsStaffJobVo> result =  staffJobService.queryAllStaffJob();
        if (result.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }


}
