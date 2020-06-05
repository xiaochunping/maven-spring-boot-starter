package com.study.starter.demo.controller;

import com.study.starter.demo.vo.ProductVO;
import com.study.starter.log.MyLog;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @MyLog(desc = "查询商品")
    @GetMapping("/list")
    public String list() {
        System.out.println("查询商品");
        return "ok";
    }

    @MyLog(desc = "添加商品")
    @PostMapping("/save")
    public String save(@RequestBody ProductVO productVO) {
        System.out.println("添加商品");
        return "ok";
    }

    @MyLog(desc = "删除商品")
    @GetMapping("/delete")
    public String delete(@RequestParam("productId") Long productId) {
        System.out.println("删除商品");
        return "ok";
    }

    @MyLog(desc = "获取商品详情")
    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable("productId") Long productId) {
        System.out.println("商品详情");
        return "ok";
    }

}
