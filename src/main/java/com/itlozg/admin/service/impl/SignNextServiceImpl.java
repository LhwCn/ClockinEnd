package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.SignLegwork;
import com.itlozg.admin.entity.SignNext;
import com.itlozg.admin.entity.TPBaseEntity;
import com.itlozg.admin.entity.dto.SignNextDto;
import com.itlozg.admin.entity.vo.SignNextVO;
import com.itlozg.admin.mapper.SignNextMapper;
import com.itlozg.admin.service.ISignLegworkService;
import com.itlozg.admin.service.ISignNextService;
import com.itlozg.admin.model.request.PublicNumberMsgRequest;
import com.itlozg.admin.model.request.SignNextAllRequest;
import com.itlozg.admin.model.request.SignNextRequest;
import com.itlozg.admin.model.response.SignNextAllResponse;
import com.itlozg.admin.model.response.SignNextResponse;
import com.itlozg.admin.model.response.SignRuleResponse;
import com.itlozg.admin.service.ISignNextAllService;
import com.itlozg.admin.service.SignTemplateService;
import com.itlozg.admin.util.BeanCopier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SignNextServiceImpl extends BaseServiceImpl<SignNextMapper, SignNext> implements ISignNextService {

    @Autowired
    private ISignNextAllService iSignNextAllService;

    @Autowired
    private ISignLegworkService iSignLegworkService;

    @Autowired
    private SignTemplateService signTemplateService;

    @Override
    public SignNextResponse getSignInfoByUserDay(String userId, String dayVal) {
        SignNext result = baseMapper.getSignInfoByUserDay(dayVal, userId);
        if (result != null) {
            return BeanCopier.copy(result, SignNextResponse.class);
        } else {
            return new SignNextResponse();
        }
    }


    @Override
    public SignNextResponse getSignNextById(String nextId) {
        //SignNext existing = selectById(nextId);  //这是旧的下面是我写的
        SignNext existing = baseMapper.selectById(nextId);
        if (existing != null) {
            return BeanCopier.copy(existing, SignNextResponse.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public SignNextResponse saveSignInfoNew(String userId, SignNextRequest request, SignRuleResponse signRule) {
        SignNext existing = null;
        //如果请求前一天的数据不为null 并且 打卡规则的类型不为null 并且 打卡规则的类型是1  这样的话就是他是来补卡的
        if (StringUtils.isNotEmpty(request.getIsLastDay()) && StringUtils.isNotEmpty(signRule.getRuleType()) && signRule.getRuleType().equals("1")) {
//            //region 补卡---打昨日卡  (existing存储的是昨天的打卡信息)
//            existing = getSignInfoByUserLastday(userId);
//            if (existing != null) {
//                request.setId(existing.getId());
//                request.setSignDate(existing.getSignDate());
//                request.setRuleId(existing.getRuleId());
//            } else {
//                return null;
//            }
//            //endregion
        } else {

            //----------------------------打卡---------------------------

            System.out.println("////"+request);

            try {
                //获取今日打卡计划
                existing = getSignRecordByUserToday(userId);
                if (existing != null) {
                    request.setId(existing.getId());
                    request.setSignDate(existing.getSignDate());
                    request.setRuleId(existing.getRuleId());
                }
            } catch (Exception ex) {

            }
        }

        if (existing != null) {
            //region 今日应打卡
            try {
                //该计划所有内勤打卡记录
                List<SignNextAllResponse> allResponses = iSignNextAllService.getSignDetailByNextId(existing.getId());

                /**
                 * 上下班打卡 0：上班打卡 1：下班打卡
                 */
//                if (allResponses == null || allResponses.size() == 0) {
//                    System.out.println("8");
//                    request.setCommute("0"); //固定上下班2次打卡  没有记录则为上班
//                }
//                //判断是自由打卡
//                if (StringUtils.isNotEmpty(existing.getSignType()) && existing.getSignType().equals("1")) {
//                    //判断是自由打卡
//                    //region 获取最后一次打卡类型
//                    if (allResponses != null && allResponses.size() > 0) {
//                        if (StringUtils.isNotEmpty(request.getIsLastDay())) {
//                            request.setCommute("1");//如果是昨日打卡   一直是下班
//                        } else {
//                            if (allResponses.get(allResponses.size() - 1).getSignType().equals("0")) {
//                                request.setCommute("1");//如果最后一次是上班   本次为下班
//                            } else {
//                                //是否为更新打卡 为1时是更新打卡
//                                if (StringUtils.isNotBlank(request.getIsUpdate()) && "1".equals(request.getIsUpdate())) {
//                                    request.setCommute("1");
//                                }
//                            }
//                        }
//                    }
//                    //endregion
//                } else {
//                    //如果是固定打卡  区分是否4次打卡
//                    if (StringUtils.isNotEmpty(signRule.getIsQuartic()) && signRule.getIsQuartic().equals("1")) {
//                        //region  固定上下班  4次打卡    为1的话是4次打卡
//                        if (allResponses != null && allResponses.size() > 0) {
//                            if (allResponses.get(allResponses.size() - 1).getSignType().equals("0")) {
//                                request.setCommute("1");//如果最后一次是上班   本次为下班
//                                System.out.println("7");
//
//                            } else {
//                                //如果是更新的话 更新的是下班 本次也是下班
//                                if (StringUtils.isNotBlank(request.getIsUpdate()) && "1".equals(request.getIsUpdate())) {
//                                    request.setCommute("1");
//                                    System.out.println("6");
//
//                                }
//                            }
//                        }
//                        //endregion
//                    } else {
//                        //region  固定上下班  2次打卡-----------------------------------------使用的---------------------
//                        //获取最后一次打卡类型
//                        if (allResponses != null && allResponses.size() > 0) {
//                            System.out.println("9");
//                            request.setCommute("1");//固定上下班2次打卡  有记录则后续为下班
//                        }
//                        //endregion
//                    }
//                }

                System.out.println("进入"+request.getCommute());

                // 分别为上下班赋地址值
                //这个地方是为了防止打卡过程中获取位置失败而做的兜底措施吗？？
                if (request.getCommute().equals("1") && StringUtils.isEmpty(request.getLeaveLatitude())) {
                    //如果是下班  并且值是空  则取值come信息
                    request.setLeaveLatitude(request.getComeLatitude());
                    request.setLeaveLongitude(request.getComeLongitude());
                    request.setLeaveTime(request.getComeTime());
                    request.setLeaveAddress(request.getComeAddress());
                    request.setLeaveAddressName(request.getComeAddressName());
                }

                if (request.getCommute().equals("0") && StringUtils.isEmpty(request.getComeLatitude())) {
//                if (request.getCommute().equals("1") && StringUtils.isEmpty(request.getComeLatitude())) {
                    //如果是上班  并且值是空  则取值leave信息
                    request.setComeLatitude(request.getLeaveLatitude());
                    request.setComeLongitude(request.getLeaveLongitude());
                    request.setComeTime(request.getLeaveTime());
                    request.setComeAddress(request.getLeaveAddress());
                    request.setComeAddressName(request.getLeaveAddressName());
                }

            } catch (Exception ex) {

            }

            //---------------------------------------------------------------------------------------------------------------------

            //发送打卡通知
            PublicNumberMsgRequest publicNumberMsgRequest = new PublicNumberMsgRequest();
            if (request.getCommute().equals("0")) {
                //region 上下班打卡 0：上班打卡 1：下班打卡

                //设置上班打卡方式 并且发送通知到个人
                if (StringUtils.isNotBlank(request.getSignWay())) {
                    existing.setSignWay(request.getSignWay());//0：正常打卡 1：自动打卡（上班）
                    if (request.getSignWay().equals("0")) {
                        publicNumberMsgRequest.setTitle("正常打卡通知");
                    } else {
                        publicNumberMsgRequest.setTitle("自动打卡通知");
                    }
                } else {
                    //如果前端没有传来打卡信息的话那么就默认自动打卡
                    existing.setSignWay(SignNextRequest.SIGN_WAY_NORMAL);
                    publicNumberMsgRequest.setTitle("自动打卡通知");
                }
                //设置上班打卡地点名字
                System.out.println(request.getComeAddressName());
                if (StringUtils.isNotEmpty(request.getComeAddressName())) {
                    existing.setComeAddressName(request.getComeAddressName());
                }
                //设置上班打卡备注
                if (StringUtils.isNotEmpty(request.getRemarks())) {
                    existing.setComeRemarks(request.getRemarks());
                }
                //设置上班打卡时间
                if (StringUtils.isEmpty(existing.getComeTime())) {
                    existing.setComeTime(request.getComeTime());
                }
                //设置上班打卡经纬度
                existing.setComeLongitude(request.getComeLongitude());//上班打卡经度
                existing.setComeLatitude(request.getComeLatitude());//上班打卡纬度
                //设置上班打卡地点
                if (StringUtils.isNotBlank(request.getComeAddress())) {
                    existing.setComeAddress(request.getComeAddress());
                }
                publicNumberMsgRequest.setContent(" " + "上班打卡成功！");

                //endregion
            } else {
                //region 如果下班时间不为空加更新时间标识
                //（会有人重新打卡配置一个更新记录）
                if (StringUtils.isNotBlank(request.getLeaveTime())) {
                    existing.setUpdateTimeFlag(SignNextRequest.UPDATE_TIME_YES);//是
                    existing.setLeaveTime(request.getLeaveTime());
                } else {
                    existing.setUpdateTimeFlag(SignNextRequest.UPDATE_TIME_NO);//否
                }
                //设置下班打卡方式  并且发送通知到个人
                if (StringUtils.isNotBlank(request.getSignWay())) {
                    existing.setSignWayPm(request.getSignWay());//0：正常打卡 1：自动打卡(下班）
                    if (request.getSignWay().equals("0")) {
                        publicNumberMsgRequest.setTitle("正常打卡通知");
                    } else {
                        publicNumberMsgRequest.setTitle("自动打卡通知");
                    }
                } else {
                    existing.setSignWayPm(SignNextRequest.SIGN_WAY_NORMAL);
                    publicNumberMsgRequest.setTitle("正常打卡通知");
                }
                //设置下班打卡地址名称
                if (StringUtils.isNotEmpty(request.getLeaveAddressName())) {
                    existing.setLeaveAddressName(request.getLeaveAddressName());
                }
                //设置下班打卡备注
                if (StringUtils.isNotEmpty(request.getRemarks())) {
                    existing.setLeaveRemarks(request.getRemarks());
                }
                //设置下班打卡时间和经纬度
                existing.setLeaveLongitude(request.getLeaveLongitude());//下班打卡经度
                existing.setLeaveLatitude(request.getLeaveLatitude());//下班打卡纬度
                //设置下班打卡地址
                if (StringUtils.isNotBlank(request.getLeaveAddress())) {
                    existing.setLeaveAddress(request.getLeaveAddress());
                }
                publicNumberMsgRequest.setContent(" " + "下班打卡成功!");
                //endregion
            }
            /**
             * rootVal
             * 默认空着就行
             * android  root  1
             * ios          越狱   2
             */
            //设置是否root打卡
            if (StringUtils.isNotEmpty(request.getRootVal())) {//是否root打卡
                existing.setRootVal(request.getRootVal());
            }
            //设置最后更新的人
            existing.setLastUpdatedBy(userId);
            //设置最后更新的日期
            existing.setLastUpdateDate(new Date());
            //判断是更新还是新增，但是后面做了啥操作不知道
//            super.insertOrUpdate(existing);
            SignNext signNext = baseMapper.selectById(existing.getId());
            if (signNext != null) {
                baseMapper.updateById(existing);
            } else {
                baseMapper.insert(existing);
            }
            //设置request对象的id，设置request对象的打卡日期
            request.setId(existing.getId());
            request.setSignDate(existing.getSignDate());
            saveSignDetail(request, userId);//保存到明细表

            //封装公众号消息提醒
            String[] target = new String[1];
            target[0] = userId;
            publicNumberMsgRequest.setReceiversId(target);//接收人
            publicNumberMsgRequest.setPublicNumber("公众号：打卡通知");
            //发送钉钉、weitalk公众号消息提醒  联系潍柴学习一下
//            iDingMessageService.sendPublicNumberMsg(publicNumberMsgRequest);

            return BeanCopier.copy(existing, SignNextResponse.class);

            //endregion
            //-------------------------------------------------------------------------------------------

        } else {
            //region 今日不需要打卡  创建一条非必须打卡的数据  isMust----IS_MUST_NO
            PublicNumberMsgRequest publicNumberMsgRequest = new PublicNumberMsgRequest();
            SignNext next = new SignNext();
            next.setSignDate(new Date());
            next.setRuleId(request.getRuleId());
            next.setUserId(userId);
            next.setIsMust(SignNextRequest.IS_MUST_NO);//是否必须打卡：否
            request.setCommute("0");
            if (request.getCommute().equals("0") && StringUtils.isEmpty(request.getComeLatitude())) {
                //如果是上班  并且值是空  则取值leave信息
                next.setComeLatitude(request.getLeaveLatitude());
                next.setComeLongitude(request.getLeaveLongitude());
                next.setComeTime(request.getLeaveTime());
                next.setComeAddress(request.getLeaveAddress());
                next.setComeAddressName(request.getLeaveAddressName());

                request.setComeLatitude(request.getLeaveLatitude());
                request.setComeLongitude(request.getLeaveLongitude());
                request.setComeTime(request.getLeaveTime());
                request.setComeAddress(request.getLeaveAddress());
                request.setComeAddressName(request.getLeaveAddressName());
            }

            //region 原有逻辑
            //设置上班打卡
            if (request.getCommute().equals("0")) {//上下班打卡 0：上班打卡 1：下班打卡
                //设置上班打卡时间
                next.setComeTime(request.getComeTime());
                next.setComeLongitude(request.getComeLongitude());//上班打卡经度
                next.setComeLatitude(request.getComeLatitude());//上班打卡纬度
                //设置上班打卡地点
                if (StringUtils.isNotBlank(request.getComeAddress())) {
                    next.setComeAddress(request.getComeAddress());
                }

                //设置上班打卡地点名称
                if (StringUtils.isNotEmpty(request.getComeAddressName())) {
                    next.setComeAddressName(request.getComeAddressName());
                }
                //设置上班打卡备注
                if (StringUtils.isNotEmpty(request.getRemarks())) {
                    next.setComeRemarks(request.getRemarks());
                }
                publicNumberMsgRequest.setContent(" " + "上班打卡成功！");
            } else {//理论上不会出现
                //下班打卡
                //设置下班打卡时间
                next.setLeaveTime(request.getLeaveTime());
                next.setLeaveLongitude(request.getLeaveLongitude());//下班打卡经度
                next.setLeaveLatitude(request.getLeaveLatitude());//下班打卡纬度
                //设置下班打卡地点
                if (StringUtils.isNotBlank(request.getLeaveAddress())) {
                    next.setLeaveAddress(request.getLeaveAddress());
                }

                //设置下班打卡地点名称
                if (StringUtils.isNotEmpty(request.getLeaveAddressName())) {
                    next.setLeaveAddressName(request.getLeaveAddressName());
                }
                //设置下班打卡备注
                if (StringUtils.isNotEmpty(request.getRemarks())) {
                    next.setLeaveRemarks(request.getRemarks());
                }
                publicNumberMsgRequest.setContent(" " + "下班打卡成功！");
            }
            //endregion
            //设置正常打卡方式
            next.setSignWay(SignNextRequest.SIGN_WAY_NORMAL);//正常打卡
            next.setSignWayPm(SignNextRequest.SIGN_WAY_NORMAL);//正常打卡
            next.setSignType(request.getSignType());//0：固定打卡 1：自由打卡
            //如果有非工作时间有打卡规则，那么缺失的这些也要补上
            if (signRule != null) {
                //设置打卡类型 0：固定打卡 1：自由打卡
                next.setSignType(signRule.getRuleType());
                //设置上班必须打卡时间
                next.setComeMustTime(signRule.getWorkTime());
                //设置下班必须打卡时间
                next.setLeaveMustTime(signRule.getOffTime());
                //设置下班提醒内容
                next.setTipMsg(signRule.getTipMsg());
                //设置下班打卡提醒时间
                next.setTipTime(signRule.getTipTime());
                //设置是否跨天打卡
                next.setCrossDay(signRule.getCrossDay());
                //设置打卡规则id
                next.setRuleId(signRule.getId());
            }
            next.setCreatedBy(userId);
            next.setLastUpdatedBy(userId);
            next.setCreationDate(new Date());
            next.setLastUpdateDate(new Date());
            //super.insert(next); //潍柴的 目前解决不了
            baseMapper.insert(next);
            request.setId(next.getId());
            request.setSignDate(next.getSignDate());
            saveSignDetail(request, userId);//保存到明细表

            //发送打卡通知到某些目标
            String[] target = new String[1];
            target[0] = userId;
            publicNumberMsgRequest.setReceiversId(target);//接收人
            publicNumberMsgRequest.setTitle("正常打卡通知");
            publicNumberMsgRequest.setPublicNumber("公众号：打卡通知");
            //发送钉钉、weitalk公众号消息提醒   联系潍柴学习一下
//            iDingMessageService.sendPublicNumberMsg(publicNumberMsgRequest);

            SignNextResponse signNextResponse = BeanCopier.copy(next, SignNextResponse.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String signDateStr = sdf.format(next.getSignDate());
            signNextResponse.setSignDateStr(signDateStr);
            return signNextResponse;
            //endregion
        }
    }

    @Override
    public SignNextResponse getSignInfoByUserToday(String userId, boolean isNeedDeal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //dateNow 当日
        String dateNow = sdf.format(new Date());
        Date date = dateCalcuate(-1);
        //dateBefore 昨天
        String dateBefore = sdf.format(date);
        List<SignNext> list = baseMapper.getSignInfoByUserToday(dateNow, dateNow, userId);
        if (list.size() == 0 && isNeedDeal) {
            //当日没有打卡计划，并且在当前时间在特殊时段内，判断昨天有没有打卡计划，就是可能存在的夜班，跨天打卡的情况
            list = baseMapper.getSignInfoByUserToday(dateBefore, dateBefore, userId);
        }
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            SignNext signNext = list.get(0);
            Date signDate = signNext.getSignDate();
            if (isNow(signDate) || isBefore(signDate)) {
                //如果当前用户有一条打卡计划，并且时间是今天或者昨天，那么把打卡计划对象转换为打卡计划响应对象，加上一个code状态值
                return BeanCopier.copy(signNext, SignNextResponse.class);
            } else {
                //如果有打卡计划，但不是今天或者昨天的计划，判断实际下班打卡时间是否为空，是否包含空字符串，不包含返回响应对象
                if (StringUtils.isNotBlank(signNext.getLeaveTime())) {
                    return BeanCopier.copy(signNext, SignNextResponse.class);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public SignNextResponse getSignInfoByUserToday(String userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        //查询当前用户在当日的打卡计划
        List<SignNext> list = baseMapper.getSignInfoByUserToday(dateNow, dateNow, userId);
        //用户没有打卡计划
        if (list.size() == 0) {
            return null;
        }
        //有一条计划
        if (list.size() == 1) {
            SignNext signNext = list.get(0);
            Date signDate = signNext.getSignDate();
            if (isNow(signDate) || isBefore(signDate)) {
                //如果打卡计划中的日期是当日或昨天，将打卡计划对象转换成响应对象返回，响应对象就是在打卡计划信息的基础上加上了一个code状态值
                return BeanCopier.copy(signNext, SignNextResponse.class);
            } else {
                //如果有打卡计划记录，而且日期不是今天或者昨天，而且不为空，不包含空字符串，那么转换成响应对象返回
                if (StringUtils.isNotBlank(signNext.getLeaveTime())) {
                    return BeanCopier.copy(signNext, SignNextResponse.class);
                } else {
                    return null;
                }
            }
        } else {
            //有一条以上的打卡计划，说明定时任务可能因为各种原因执行了两次，所以前台无法打卡
            // 没有打卡计划不会打卡，有超过一条打卡计划也不能打卡，只有一条打卡计划才能打卡
            return null;
        }
    }


    //时间计算
    private static Date dateCalcuate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        Date date = calendar.getTime();
        return date;
    }


    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 判断时间是不是昨天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isBefore(Date date) {
        //当前时间
        Date beforeDay = dateCalcuate(-1);//昨天
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取昨天的日期
        String nowDay = sf.format(beforeDay);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }


    /**
     * 获取打卡信息
     *
     * @param userId
     * @return
     */
    @Override
    public SignNextResponse getSignInfoByUserTodayOnlyInfo(String userId) {
        //获取当天打卡信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        //获取两天内的打卡信息（考虑跨天情况）
        List<SignNext> list = baseMapper.getSignInfoByUserTodayOnlyInfo(dateNow, dateNow, userId);
        //获取不到没有则不需要打卡
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return BeanCopier.copy(list.get(0), SignNextResponse.class);
        }
    }

    /**
     * 获取打卡信息  前一天
     *
     * @param userId
     * @return
     */
    @Override
    public SignNext getSignInfoByUserLastday(String userId) {
        //获取昨天打卡信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        Date date = dateCalcuate(-1);//昨天
        String dateBefore = sdf.format(date);
        //获取两天内的打卡信息（考虑跨天情况）
        List<SignNext> list = baseMapper.getSignInfoByUserToday(dateBefore, dateBefore, userId);
        if (list.size() == 0) {//获取不到没有则不需要打卡
            return null;
        }
        if (list.size() == 1) {
            SignNext signNext = list.get(0);
            return signNext;
        } else {
            return null;
        }
    }

    /**
     * 获取当前考勤组昨天的所有打卡计划，并为外勤打卡计划填充信息
     */
    @Override
    public List<SignNextVO> getSignInfoAllLastday(String groupId) {
        List<SignNextVO> list = baseMapper.getSignInfoAllToday(groupId);
        if (list.size() == 0) {
            return null;
        }

        // 遍历当前考勤组昨天的每一个计划，如果是外勤计划，查找当前用户和昨天日期的所有外勤打卡记录，为这些外勤计划填充一些信息
        Date date = dateCalcuate(-1);
        list = list.stream().map(signNextVO -> {
            if (signNextVO.getLegworkFlag() == 1) {
                List<SignLegwork> legworks = iSignLegworkService.list(
                    new LambdaQueryWrapper<SignLegwork>().eq(SignLegwork::getUserId, signNextVO.getUserId()).eq(SignLegwork::getCreationDate, date)
                );
                if (legworks.size() > 0) {
                    // 从第一条外勤打卡数据中取值，并为计划赋值
                    signNextVO.setComeAddress(legworks.get(0).getSignTime());
                    signNextVO.setComeLatitude(legworks.get(0).getSignLatitude());
                    signNextVO.setComeLongitude(legworks.get(0).getSignLongitude());
                    signNextVO.setSignFlag(3); // 打卡标识： 0 正常；1 请假；2 出差；3 外勤；4 调休
                }
            }
            return signNextVO;
        }).collect(Collectors.toList());
        return list;
    }


    @Override
    public void autoSave() {
//        signTemplateService.
        baseMapper.autoSave();
    }

    @Override
    public Boolean autoSaveCarInfo(SignNext signNext) {
        return baseMapper.autoSaveCarInfo(signNext);
    }

    @Override
    public List<SignNextVO> getAutoNextInfo() {
        return baseMapper.getAutoNextInfo();
    }

    @Override
    public List<SignNextVO> getSureUser() {
        return baseMapper.getSureUser();
    }

    @Override
    public List<SignNextVO> getFreeUser() {
        return baseMapper.getFreeUser();
    }

    /**
     * 获取打卡信息  今天
     *
     * @param userId
     * @return
     */
    public SignNext getSignRecordByUserToday(String userId) {
        //获取当天打卡信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        //获取两天内的打卡信息（考虑跨天情况）
        List<SignNext> list = baseMapper.getSignInfoByUserTodayOnlyInfo(dateNow, dateNow, userId);
        //获取不到没有则不需要打卡
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


    /**
     * 保存到明细表
     *
     * @param request
     * @param userId
     */
    private void saveSignDetail(SignNextRequest request, String userId) {
        System.out.println("----[[[[----"+request.getCommute());

        //保存到打卡记录表
        SignNextAllRequest signDetail = new SignNextAllRequest();
        signDetail.setUserId(userId);
        signDetail.setSignId(request.getId());
        signDetail.setSignDate(request.getSignDate());
        signDetail.setRuleType(request.getSignType());//0：固定打卡 1：自由打卡
        signDetail.setSignType(request.getCommute());//0：上班打卡 1：下班打卡
        //明细中设置是否root
        if (StringUtils.isNotEmpty(request.getRootVal())) {//是否root打卡
            signDetail.setRootVal(request.getRootVal());
        }
        //明细中设置唯一编码
        if (StringUtils.isNotEmpty(request.getUniqueCode())) {//是否root打卡
            signDetail.setUniqueCode(request.getUniqueCode());
        }
        //明细中设置备注
        if (StringUtils.isNotEmpty(request.getRemarks())) {//是否有备注
            signDetail.setRemarks(request.getRemarks());
            signDetail.setSignRemarks(request.getRemarks());
        }
        //明细中设置上班打卡的打卡方式
        if (request.getCommute().equals("0")) {
            if (StringUtils.isNotBlank(request.getSignWay())) {
                signDetail.setSignWay(request.getSignWay());//0：正常打卡 1：自动打卡
            } else {
                //如果没有打卡方式 设置打卡方式为正常打卡
                signDetail.setSignWay(SignNextRequest.SIGN_WAY_NORMAL);
            }
            signDetail.setSignTime(request.getComeTime());//打卡时间
            signDetail.setSignLongitude(request.getComeLongitude());//打卡经度
            signDetail.setSignLatitude(request.getComeLatitude());//打卡纬度
            //设置上班打卡图片
            if (StringUtils.isNotBlank(request.getComePhoto())) {
                signDetail.setSignPhoto(request.getComePhoto());
            }
            if (StringUtils.isNotBlank(request.getComeAddress())) {
                signDetail.setSignAddress(request.getComeAddress());//打卡地址
            }
            if (StringUtils.isNotBlank(request.getComeAddressName())) {
                signDetail.setSignAddressName(request.getComeAddressName());//打卡地址名称
            }
        } else {
            //下班打卡
            //设置下班打卡方式
            if (StringUtils.isNotBlank(request.getSignWay())) {
                signDetail.setSignWayPm(request.getSignWay());//0：正常打卡 1：自动打卡
            } else {
                signDetail.setSignWayPm(SignNextRequest.SIGN_WAY_NORMAL);
            }
            //设置下班打卡时间
            signDetail.setSignTime(request.getLeaveTime());
            signDetail.setSignLongitude(request.getLeaveLongitude());//打卡经度
            signDetail.setSignLatitude(request.getLeaveLatitude());//打卡纬度
            //设置下班打卡图片
            if (StringUtils.isNotBlank(request.getLeavePhoto())) {
                signDetail.setSignPhoto(request.getLeavePhoto());
            }
            if (StringUtils.isNotBlank(request.getLeaveAddress())) {
                signDetail.setSignAddress(request.getLeaveAddress());//打卡地址
            }
            if (StringUtils.isNotBlank(request.getLeaveAddressName())) {
                signDetail.setSignAddressName(request.getLeaveAddressName());//打卡地址名称
            }
        }
        //明细中设置打卡规则id
        signDetail.setRuleId(request.getRuleId());
        iSignNextAllService.saveSignInfo(signDetail);
    }

}
