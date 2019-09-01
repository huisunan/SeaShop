package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.StaffClassControllerApi;
import com.rsdtm.supermarket.bms.service.StaffClassService;
import com.rsdtm.supermarket.mbg.model.BmsStaffClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staffClass")
public class StaffClassController implements StaffClassControllerApi {

    @Autowired
    StaffClassService staffClassService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addStaffClass(
            @RequestBody BmsStaffClass bmsStaffClass) {
        int result = staffClassService.addStaffClass(bmsStaffClass);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteStaffClassById(
            @RequestParam(required = true) Integer id) {
        int result = staffClassService.deleteStaffClassById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateStaffClass(
             BmsStaffClass bmsStaffClass) {
        int result = staffClassService.updateStaffClass(bmsStaffClass);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsStaffClass> queryStaffClassById(
            @RequestParam(required = true) Integer id) {
        BmsStaffClass result = staffClassService.queryStaffClassById(id);
        if (result == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("all")
    public ResponseEntity<List<BmsStaffClass>> queryAllStaffClass() {
        List<BmsStaffClass> result = staffClassService.queryAllStaffClass();
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
}
