package com.api.controller;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        System.out.println("FILE IS "+file.toString());
        if (Helper.checkExcelFormat(file)) {
            //true

            this.productService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

    //API 1
    @PostMapping("/total_items")
    public ResponseEntity<Integer> getTotalItemsSoldInQ3(
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("department") String department) throws ParseException {

        int totalItems = productService.getTotalItemsSoldInQ3(startDate, endDate, department);
        return ResponseEntity.ok(totalItems);
    }

    //API 4
    @GetMapping("/api/monthly_sales")
    public List<Integer> getMonthlySales(@RequestParam("year") Integer year,@RequestParam("product") String product){
        return productService.getMonthlySales(year,product);

    }

    @GetMapping("api/percentage_of_department_wise_sold_items")
    public HashMap departmentWisePercentage(@RequestParam("start_date")Date startDate, @RequestParam("end_date") Date endDate) throws ParseException {
        return productService.departmentWisePercentage(startDate,endDate);
    }

}
