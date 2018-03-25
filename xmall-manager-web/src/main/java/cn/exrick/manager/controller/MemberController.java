package cn.exrick.manager.controller;

import cn.exrick.common.dto.MemberDto;
import cn.exrick.common.dto.CommonDto;
import cn.exrick.common.dto.Member;
import cn.exrick.common.dto.MemberLoginRegist;
import cn.exrick.common.pojo.DataTablesResult;
import cn.exrick.common.pojo.Result;
import cn.exrick.common.pojo.TbMember;
import cn.exrick.common.service.MemberService;
import cn.exrick.common.utils.GeetestLib;
import cn.exrick.common.utils.ResultUtil;
import cn.exrick.common.service.LoginService;
import cn.exrick.common.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 *
 * @author Exrick
 * @date 2017/8/11
 */
@RestController
@Api(description= "会员管理")
public class MemberController {

    final static Logger log= LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/member/list",method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取会员列表")
    public DataTablesResult getMemberList(int draw, int start, int length, String searchKey, @RequestParam("search[value]") String search,
                                          String minDate, String maxDate,
                                          @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id", "username","sex", "phone", "email", "address", "created", "updated", "state"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=memberService.getMemberList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/member/list/remove",method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索已删除会员列表")
    public DataTablesResult getDelMemberList(int draw, int start, int length,String searchKey,@RequestParam("search[value]") String search,
                                             String minDate, String maxDate,
                                             @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id", "username","sex", "phone", "email", "address", "created", "updated", "state"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=memberService.getRemoveMemberList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/member/count",method = RequestMethod.GET)
    @ApiOperation(value = "获得总会员数目")
    public DataTablesResult getMemberCount(){

        return memberService.getMemberCount();
    }

    @RequestMapping(value = "/member/count/remove",method = RequestMethod.GET)
    @ApiOperation(value = "获得移除总会员数目")
    public DataTablesResult getRemoveMemberCount(){

        return memberService.getRemoveMemberCount();
    }

    @RequestMapping(value = "/member/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加会员")
    public Result<TbMember> createMember(@ModelAttribute MemberDto memberDto){

        TbMember newTbMember = memberService.addMember(memberDto);
        return new ResultUtil<TbMember>().setData(newTbMember);
    }

    @RequestMapping(value = "/member/stop/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "停用会员")
    public Result<TbMember> stopMember(@PathVariable Long id){

        TbMember tbMember = memberService.alertMemberState(id,0);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/start/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "启用会员")
    public Result<TbMember> startMember(@PathVariable Long id){

        TbMember tbMember = memberService.alertMemberState(id,1);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/remove/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "移除会员")
    public Result<TbMember> removeMember(@PathVariable Long id){

        TbMember tbMember = memberService.alertMemberState(id,2);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/del/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "彻底删除会员")
    public Result<TbMember> deleteMember(@PathVariable Long id){

        memberService.deleteMember(id);
        return new ResultUtil<TbMember>().setData(null);
    }

    @RequestMapping(value = "/member/changePass/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "修改会员密码")
    public Result<TbMember> changeMemberPassword(@PathVariable Long id,@ModelAttribute MemberDto memberDto){

        TbMember tbMember = memberService.changePassMember(id,memberDto);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/update/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "修改会员信息")
    public Result<TbMember> updateMember(@PathVariable Long id,@ModelAttribute MemberDto memberDto){

        TbMember tbMember = memberService.updateMember(id,memberDto);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "通过ID获取会员信息")
    public Result<TbMember> getMemberById(@PathVariable Long id){

        TbMember tbMember = memberService.getMemberById(id);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @RequestMapping(value = "/member/username",method = RequestMethod.GET)
    @ApiOperation(value = "验证注册名是否存在")
    public Boolean validateUsername(String username){

        if(memberService.getMemberByUsername(username)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/member/phone",method = RequestMethod.GET)
    @ApiOperation(value = "验证注册手机是否存在")
    public Boolean validatePhone(String phone){

        if(memberService.getMemberByPhone(phone)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/member/email",method = RequestMethod.GET)
    @ApiOperation(value = "验证注册邮箱是否存在")
    public Boolean validateEmail(String email){

        if(memberService.getMemberByEmail(email)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/member/edit/{id}/username",method = RequestMethod.GET)
    @ApiOperation(value = "验证编辑用户名是否存在")
    public Boolean validateEditUsername(@PathVariable Long id,String username){

        if(memberService.getMemberByEditUsername(id,username)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/member/edit/{id}/phone",method = RequestMethod.GET)
    @ApiOperation(value = "验证编辑手机是否存在")
    public Boolean validateEditPhone(@PathVariable Long id,String phone){

        if(memberService.getMemberByEditPhone(id,phone)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/member/edit/{id}/email",method = RequestMethod.GET)
    @ApiOperation(value = "验证编辑邮箱是否存在")
    public Boolean validateEditEmail(@PathVariable Long id,String email){

        if(memberService.getMemberByEditEmail(id,email)!=null){
            return false;
        }
        return true;
    }


    @RequestMapping(value = "/member/geetestInit",method = RequestMethod.GET)
    @ApiOperation(value = "极验初始化")
    public String geetesrInit(HttpServletRequest request){

        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String resStr = "{}";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);

        resStr = gtSdk.getResponseStr();

        return resStr;
    }

    @RequestMapping(value = "/member/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Member> login(@RequestBody MemberLoginRegist memberLoginRegist,
                                HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String challenge=memberLoginRegist.getChallenge();
        String validate=memberLoginRegist.getValidate();
        String seccode=memberLoginRegist.getSeccode();

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        Member member=new Member();
        if (gtResult == 1) {
            // 验证成功
            member=loginService.userLogin(memberLoginRegist.getUserName(), memberLoginRegist.getUserPwd());
        }
        else {
            // 验证失败
            member.setState(0);
            member.setMessage("验证失败");
        }
        return new ResultUtil<Member>().setData(member);
    }

    @RequestMapping(value = "/member/checkLogin",method = RequestMethod.GET)
    @ApiOperation(value = "判断用户是否登录")
    public Result<Member> checkLogin(@RequestParam(defaultValue = "") String token){

        Member member=loginService.getUserByToken(token);
        return new ResultUtil<Member>().setData(member);
    }

    @RequestMapping(value = "/member/loginOut",method = RequestMethod.GET)
    @ApiOperation(value = "退出登录")
    public Result<Object> logout(@RequestParam(defaultValue = "") String token){

        loginService.logout(token);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/member/register",method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> register(@RequestBody MemberLoginRegist memberLoginRegist,
                                   HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String challenge=memberLoginRegist.getChallenge();
        String validate=memberLoginRegist.getValidate();
        String seccode=memberLoginRegist.getSeccode();

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        if (gtResult == 1) {
            // 验证成功
            int result=registerService.register(memberLoginRegist.getUserName(), memberLoginRegist.getUserPwd());
            if(result==0){
                return new ResultUtil<Object>().setErrorMsg("该用户名已被注册");
            }else if(result==-1){
                return new ResultUtil<Object>().setErrorMsg("用户名密码不能为空");
            }
            return new ResultUtil<Object>().setData(result);
        }
        else {
            // 验证失败
            return new ResultUtil<Object>().setErrorMsg("验证失败");
        }
    }

    @RequestMapping(value = "/member/imgaeUpload",method = RequestMethod.POST)
    @ApiOperation(value = "用户头像上传")
    public Result<Object> imgaeUpload(@RequestBody CommonDto common){

        String imgPath = memberService.imageUpload(common.getUserId(),common.getToken(),common.getImgData());
        return new ResultUtil<Object>().setData(imgPath);
    }
}
