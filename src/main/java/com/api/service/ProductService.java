package com.api.service;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void save(MultipartFile file) {

        try {
//            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            List<Product> products=Helper.processExcelFile(file);
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    public int getTotalItemsSoldInQ3(Date startDate, Date endDate, String department) throws ParseException {
//        return productRepo.getTotalItemsSoldInQ3(startDate, endDate, department);
        System.out.println("START DATE "+startDate+" end Date "+endDate+" department "+department);
        List<Product> list=productRepo.findAll();
        int count=0;
        System.out.println("LIST IS "+list);
        for(Product pro:list){
            System.out.println("DATE is "+pro.toString());
            Date d=new SimpleDateFormat("yyyy-MM-dd").parse(pro.getDate().split(" ")[0]);
            if(startDate.compareTo(d) * d.compareTo(endDate) > 0){
                count++;
            }
        }
        return count;
    }

    public HashMap departmentWisePercentage(Date startDate, Date endDate) throws ParseException {
//        return productRepo.getTotalItemsSoldInQ3(startDate, endDate, department);
        System.out.println("START DATE "+startDate+" end Date "+endDate+" department ");
        List<Product> list=productRepo.findAll();
        int count=0;
        HashMap<String,Integer> map=new HashMap<>();
        System.out.println("LIST IS "+list);
        for(Product pro:list){
            System.out.println("DATE is "+pro.toString());
            Date d=new SimpleDateFormat("yyyy-MM-dd").parse(pro.getDate().split(" ")[0]);
            if(startDate.compareTo(d) * d.compareTo(endDate) > 0){
                if(!map.containsKey(pro.getDepartment())){
                    map.put(pro.getDepartment(),0);
                }
                else {
//                    map.put(pro.getDepartment(),map.getOrDefault(pro.getDepartment(),0)+pro.getAmount());
                    map.put(pro.getDepartment(),map.getOrDefault(pro.getDepartment(),0)+1);
                }
            }
        }
        int total_size=list.size();
        HashMap<String,Double> map2=new HashMap<>();
        for(String key:map.keySet()){
            int val=map.get(key);
            double percentage=val/total_size*100;
            map2.put(key,percentage);
        }
        return map2;
    }

    public List<Integer> getMonthlySales(Integer year,String product){
        List<Integer> res=new ArrayList<>();
        List<Product> list=productRepo.findAll();
        int count=0;
        System.out.println("LIST IS "+list);
        for(Product pro:list){
            String date=pro.getDate().split(" ")[0];
            Integer yr=Integer.parseInt(date.split("-")[0]);
            System.out.println("YEAR IS "+yr);
            if(pro.getSoftware().compareTo(product)==0&&(year==yr)){
                res.add(pro.getAmount());
            }
        }
        return res;
    }
}
