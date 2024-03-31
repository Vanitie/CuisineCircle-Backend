package com.example.server.controller;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.form.PoiForm;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.IPicService;
import com.example.server.service.IPoiService;
import com.example.server.vo.PoiVo;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController//set the controller
@Slf4j//for the lombok
@RequestMapping("/poi")//set the /poi
public class PoiController {
    @Autowired
    private IPoiService poiService;//autowired the poiService
    @Autowired
    private IPicService picService;

    @GetMapping("/list")//set the /list,use the way of get
    public Result/*return the result ,we can find it in com.example.server.vo.Result*/ list(@RequestParam(defaultValue = "1")/*set the data if we can not get it*/ int pageNum, @RequestParam(defaultValue = "10")int pageSize){
        log.info("poi list,pageNum={},pageSize={}",pageNum,pageSize);
//        var poiVo1 = new PoiVo();
//        poiVo1.name="lisi";
//        poiVo1.description="this is lisi";
//        var poiVo2 = new PoiVo();
//        poiVo2.name="zhangsan";
//        poiVo2.description="this is zhangsan";
//        List<PoiVo> poiVos=new ArrayList<>();
//        poiVos.add(poiVo1);
//        poiVos.add(poiVo2);
        Page<Poi> page=new Page<>(pageNum,pageSize);
        IPage<Poi>pageResult=poiService.page(page);
        //use the way of poiService to transfer a list
        List<Poi> poiList=pageResult.getRecords();

        List voList=new ArrayList();//set a new list
        for (Poi poi:poiList){
            PoiVo poiVo=new PoiVo();
            QueryWrapper query= new QueryWrapper();
            query.eq("poi_id",poi.getId());
            List<Pic> pics=picService.list(query);//to get the data from Pic
//            poiVo.id=poi.id;
//            poiVo.name=poi.name;
//            poiVo.description=poi.description;
            BeanUtils.copyProperties(poi,poiVo);//a method to transfer data,without using ...=...  Note:we should set the setter and getter both in Class Poi adn Class Poivo
            poiVo.setPic(pics);
            voList.add(poiVo);
        }//set the new list to the voLlist
        pageResult.setRecords(voList);//set message to transfer as poiVo
        return Result.success(pageResult);
    }
    @GetMapping("/detail/{id}")//we use the way of /,such as /detail/123
    public Result detail(@PathVariable/*it is important,because the way to use /123,we should tell the idea to use it*/ Integer id){
        log.info("poi detail id={}",id);//log,from lombok,we can show the data to the controller
//        var poiVo = new PoiVo();
//        poiVo.name="lisi";
//        poiVo.description="this is lisi";
        Poi poi=poiService.getById(id);
//        List voList=new ArrayList();//set a new list
        PoiVo poiVo=new PoiVo();
        BeanUtils.copyProperties(poi,poiVo);//this two lines can implement the Poi to PoiVo
        QueryWrapper query= new QueryWrapper();
        query.eq("poi_id",poi.getId());
        List<Pic> pics=picService.list(query);//to get the data from Pic.In fact,we get Poi first,then we transfer the Poi to PoiVo,then we get the Pic,finally we set the Pic to the PoiVo
        poiVo.setPic(pics);
//        voList.add(poiVo);

        return Result.success(poiVo);
    }
    @PostMapping("/add")
    public Result add(@RequestBody PoiForm poiForm){//use the way of post,which means we add new data,RequestBody tells maven to transfer the json to Poi
        log.info("poi detail poi={}",poiForm);
        Poi poi=new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poiService.saveMain(poi,poiForm.getPic());//set add

//        PoiVo poiVo=new PoiVo();
//        BeanUtils.copyProperties(poi,poiVo);//this two lines can implement the Poi to PoiVo

        return detail(poi.getId());
    }
    @PutMapping("/edit/{id}")
    public Result edit(@RequestBody PoiForm poiForm,@PathVariable Integer id){//use the way of edit ,which means we change the data
        log.info("poi detail ={}",poiForm);
        Poi poi=new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poi.setId(id);
        poiService.updateMain(poi,poiForm.getPic());
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id){//use the way of delete.which means we delete the data

        log.info("poi delete id={}",id);
//        poi.setId(id);
//        poiService.removeById(id);//we use id here
        poiService.deleteMain(id);
        
        return Result.success();
    }
}
