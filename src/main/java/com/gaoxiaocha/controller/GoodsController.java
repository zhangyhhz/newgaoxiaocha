package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Goods;
import com.gaoxiaocha.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping(value = "/goods/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestParam("name") String gname,
                         @RequestParam("price")Double gprice,
                         String gdescription,
                         @RequestParam("stock")Integer gstock,
                         @RequestParam("img") MultipartFile gpicture,
                         @RequestParam("userId")Integer guserid) throws IOException {

        if(gdescription==null){
            gdescription = "没有描述";
        }
        String folder = "media";
        File file1 = new File(folder);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String filename =  UUID.randomUUID().toString()+gpicture
                .getOriginalFilename()
                .substring(gpicture.getOriginalFilename().lastIndexOf('.'));
        File file = new File(file1.getAbsolutePath()+File.separator+filename);
        gpicture.transferTo(file);
        //gpicture = goodsService.shangchuantupian(gpicture);
        Goods goods = new Goods(null,gname,gprice,gdescription,gstock,
                file1.getAbsolutePath()+File.separator+filename,guserid);
        Result result = new Result();
        if(goodsService.charu(goods)==1){
            result.setMsg("插入商品成功！");
            result.setSuccess(true);
        }
        else{
            result.setMsg("插入商品失败！");
            result.setSuccess(false);
        }
        return JSONObject.toJSONString(result);
        }
    @RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer gid){
        Goods goods = new Goods();
        goods.setGid(gid);
        Result result = new Result();
        if(goodsService.shanchu(goods)){
            result.setMsg("删除商品成功！");
            result.setSuccess(true);
        }
        else{
            result.setMsg("删除商品失败！");
            result.setSuccess(false);
        }
        return JSONObject.toJSONString(result);
    }
    @RequestMapping(value = "/goods/updatePrice", method = RequestMethod.POST)
    @ResponseBody
    public String updatePrice(Integer gid,Double gprice){
        Goods goods = new Goods();
        goods.setGid(gid);
        goods.setGprice(gprice);
        Result result = new Result();
        if(goodsService.xiugaijiage(goods)){
            result.setMsg("更新商品价格成功！");
            result.setSuccess(true);
        }
        else{
            result.setMsg("更新商品价格失败！");
            result.setSuccess(false);
        }
        return JSONObject.toJSONString(result);
    }
    @RequestMapping(value = "/goods/updateStock", method = RequestMethod.POST)
    @ResponseBody
    public String updateStock(Integer gid,Integer gstock){
        Goods goods = new Goods();
        goods.setGid(gid);
        goods.setGstock(gstock);
        Result result = new Result();
        if(goodsService.xiugaikucun(goods)){
            result.setMsg("更新商品库存成功！");
            result.setSuccess(true);
        }
        else{
            result.setMsg("更新商品库存失败！");
            result.setSuccess(false);
        }
        return JSONObject.toJSONString(result);
    }
    @RequestMapping(value = "/goods/select", method = RequestMethod.POST)
    @ResponseBody
    public String select(Integer gid) throws IOException {
        Goods goods = new Goods();
        goods.setGid(gid);
        Result<Goods> result = new Result();
        goods = goodsService.chazhao(goods);
        File file = new File(goods.getGpicture());
        FileInputStream fis = new FileInputStream(file);
        byte[] bs=new byte[fis.available()];
        fis.read(bs);
        goods.setGpicture("data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bs));
            result.setMsg("查询成功！");
            result.setData(goods);
            result.setSuccess(true);

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/goods/selectAll", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String selectAll() throws IOException {


        Result<List<Goods>> result = new Result();
        List<Goods> chazhaosuoyou = goodsService.chazhaosuoyou();

        if (chazhaosuoyou.size() > 0 || chazhaosuoyou != null) {
            for(Goods goods:chazhaosuoyou){
                File file = new File(goods.getGpicture());
                FileInputStream fis = new FileInputStream(file);
                byte[] bs=new byte[fis.available()];
                fis.read(bs);
                goods.setGpicture("data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bs));
            }
            result.setMsg("查询成功！");
            result.setData(chazhaosuoyou);
            result.setSuccess(true);
        } else {
            result.setMsg("查询失败！");
            result.setSuccess(false);
        }

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/goods/chazhao", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao(){

        Result<List<List<String>>> result = new Result();
        List<Goods> list = goodsService.chazhaosuoyou();
        List<List<String>> lists = new ArrayList<>();
        for (Goods item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getGid()));
            l.add(String.valueOf(item.getGname()));
            l.add(String.valueOf(item.getGprice()));
            l.add(String.valueOf(item.getGdescription()));
            l.add(String.valueOf(item.getGstock()));
            l.add(String.valueOf(item.getGpicture()));
            l.add(String.valueOf(item.getGuserid()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }

    @RequestMapping(value = "/goods/charu", method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody Goods goods) {
        Result result = new Result();
        if(goodsService.charu(goods)==1){
            result.setMsg("插入商品成功！");
            result.setSuccess(true);
        }
        else{
            result.setMsg("插入商品失败！");
            result.setSuccess(false);
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/goods/gengxin", method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody Goods goods) {
        Result result = new Result();
        if (goodsService.xiugai(goods) == 1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/goods/shanchu", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String shanchu(@RequestBody Goods goods){
        Result result =new Result();
        if (goodsService.shanchu(goods) == true) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }
    }

    @RequestMapping(value = "/goods/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count() {
        Result result = new Result();
        int count = goodsService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/goods/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage,@RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<Goods> list = goodsService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (Goods item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getGid()));
            l.add(String.valueOf(item.getGname()));
            l.add(String.valueOf(item.getGprice()));
            l.add(String.valueOf(item.getGdescription()));
            l.add(String.valueOf(item.getGstock()));
            l.add(String.valueOf(item.getGpicture()));
            l.add(String.valueOf(item.getGuserid()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }
}
