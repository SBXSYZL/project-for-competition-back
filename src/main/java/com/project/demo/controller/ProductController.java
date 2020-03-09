package com.project.demo.controller;

import com.project.demo.DO.ProductImageDO;
import com.project.demo.VO.CommentVO;
import com.project.demo.VO.ProDuctDetailVO;
import com.project.demo.VO.ProductFirstVO;
import com.project.demo.VO.ProductTypeVO;
import com.project.demo.error.BusinessException;
import com.project.demo.error.EmBusinessErr;
import com.project.demo.response.CommonReturnType;
import com.project.demo.response.RTStr;
import com.project.demo.service.ProductService;
import com.project.demo.utils.FileUtil;
import com.project.demo.utils.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/2 14:04
 */
@RestController
@RequestMapping("/customer")
public class ProductController extends BaseController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProductList")
    public CommonReturnType getProductList(@RequestParam(required = false) Integer shopId,
                                           @RequestParam(required = false) Integer typeId,
                                           @RequestParam Integer pageNo,
                                           @RequestParam Integer pageSize) throws BusinessException {
        List map = productService.getProductList(shopId, typeId, pageNo, pageSize);
        return CommonReturnType.create(map);
    }

    @GetMapping("/getProductTypes")
    public CommonReturnType getProductTypes() throws BusinessException {
        List<ProductTypeVO> map = productService.getProductTypes();
        return CommonReturnType.create(map);
    }

    @GetMapping("/randomSelectImg")
    public CommonReturnType randomSelectImg() throws BusinessException {
        return CommonReturnType.create(productService.randomSelectImg());
    }

    @GetMapping("/getProductDetail")
    public CommonReturnType getProductDetail(@RequestParam Integer productId) throws BusinessException {
        ProDuctDetailVO proDuctDetailVO = productService.getProductDetail(productId);
        return CommonReturnType.create(proDuctDetailVO);
    }

    @GetMapping("/addToCart")
    public CommonReturnType addToCart(@RequestParam Integer productId) throws BusinessException {
        HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) MySessionUtil.getSession().getAttribute(MySessionUtil.SHOP_CART);

        if (map == null) {
            map = new HashMap<>();
            map.put(productId, productId);
        } else {
            if (map.get(productId) == null) {
                map.put(productId, productId);
            } else {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + "    " + entry.getValue());
                }
                throw new BusinessException(EmBusinessErr.PRODUCT_ALREADY_IN_CART);
            }

        }
        MySessionUtil.getSession().setAttribute(MySessionUtil.SHOP_CART, map);

        return CommonReturnType.create(RTStr.SUCCESS);
    }

    @PostMapping("/buyProduct")
    public CommonReturnType buyProduct(@RequestParam Integer productId,
                                       @RequestParam Integer buyCnt) throws BusinessException {
        productService.buyProduct(productId, buyCnt);
        return CommonReturnType.create(RTStr.SUCCESS);
    }

    @GetMapping("/getCommentByProductId")
    public CommonReturnType getCommentByProductId(@RequestParam Integer productId,
                                                  @RequestParam Integer pageNo,
                                                  @RequestParam Integer pageSize) throws BusinessException {
        Map map = productService.getCommentByProductId(productId, pageNo, pageSize);
        return CommonReturnType.create(map);
    }

    @GetMapping("/getShopCart")
    public CommonReturnType getShopCart() throws BusinessException {
        try {
            Map<Integer, Integer> map = (Map<Integer, Integer>) MySessionUtil.getSession().getAttribute(MySessionUtil.SHOP_CART);
            if (map == null) return CommonReturnType.create(new ArrayList<>());
            List<ProductFirstVO> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                ProductFirstVO productFirstVO = productService.getProduct(entry.getKey());
                if (productFirstVO != null) {
                    list.add(productFirstVO);
                }

            }
            return CommonReturnType.create(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_SHOP_CART_LIST_ERROR);
        }
    }


    @GetMapping("/getMyOrders")
    public CommonReturnType getMyOrders(@RequestParam Integer pageNo,
                                        @RequestParam Integer pageSize) throws BusinessException {
        Map map = productService.getMyOrders(pageNo, pageSize);
        return CommonReturnType.create(map);
    }

    @GetMapping("/searchProduct")
    public CommonReturnType searchCart(@RequestParam String searchKey,
                                       @RequestParam Integer pageNo,
                                       @RequestParam Integer pageSize) throws BusinessException {
        Map map = productService.searchProduct(searchKey, pageNo, pageSize);
        return CommonReturnType.create(map);
    }

    @PostMapping("/upload")
    public CommonReturnType createProduct(@RequestParam MultipartFile file) throws BusinessException {
        String s = FileUtil.saveFile(file);
        return CommonReturnType.create(s + "已上传");
    }

    @PostMapping("/comment")
    public CommonReturnType comment(@RequestParam String comment,
                                    @RequestParam Integer productId) throws BusinessException {
        productService.writeComment(comment, productId);
        return CommonReturnType.create(RTStr.SUCCESS);
    }
}
