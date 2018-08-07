package com.mftcc.interior.report.filter.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @功能描述 消息模板枚举类。
 * @since 2017-3-8
 * @author LiuYF
 * 
 */
public enum MessageEnum {

	/**
	 * 消息分类：以下划线分隔，前缀是操作类型/消息的类型，中间是所属模块/业务，后缀是具体动作/场景。
	 * 
	 * <pre>
	 * 完成：COMPLETED_
	 * 确认：CONFIRM_
	 * 错误：ERROR_
	 * 已存在（唯一性/重复性）：EXIST_
	 * 失败：FAILED_
	 * 等待：WAIT_
	 * 先进行/前置：FIRST_
	 * 只能/范围内：ONLY_
	 * 不存在/没有：NO_
	 * 不能/不允许/不是：NOT_
	 * 成功：SUCCEED_
	 * </pre>
	 * 
	 * <pre>
	 * 所属模块/业务：
	 * 审批：APPROVAL
	 * 表单：FORM
	 * 短信：MSG
	 * ……
	 * </pre>
	 * 
	 */

	/**
	 * 这条消息的内容走丢啦！
	 */
	NONE("这条消息的内容走丢啦！", AlertType.NONE),
	/**
	 * 按照要合并文档的顺序，依次选中左侧列表区域的文件添加到右侧！
	 */
	FIRST_ARCHIVE_SEQUENCE("按照要合并文档的顺序，依次选中左侧列表区域的文件添加到右侧！", AlertType.ERROR),
	/**
	 * 该档案已封档，无法${operation}。
	 * 补充/删除
	 */
	ERROR_RECORD_SEALED("该档案已封档，无法${operation}。", AlertType.ERROR),
	/**
	 * 无法预览该文件！
	 */
	ERROR_FILE_PREVIEW("无法预览该文件！", AlertType.ERROR),
	/**
	 * 文件正在打开请稍等。
	 */
	WAIT_FILE_OPENING("文件正在打开请稍等。", AlertType.TIP),
	/**
	 * 正在${operation}，请勿重复操作！
	 */
	WAIT_OPERATION("正在${operation}，请勿重复操作！", AlertType.TIP),
	/**
	 * 正在建设中，敬请期待。
	 */
	WAIT_DEVELOPING("正在建设中，敬请期待。", AlertType.TIP),
	/**
	 * 归档文件恢复成功。
	 */
	SUCCEED_ARCHIVE_UNDO("归档文件恢复成功。", AlertType.TIP),
	/**
	 * 归档文件恢复失败！
	 */
	FAILED_ARCHIVE_UNDO("归档文件恢复失败！", AlertType.ERROR),

	/**
	 * 请至少录入一条有效分录！
	 */
	ONLY_CW_VOUCHER_ONEENTRY("请至少录入一条有效分录！", AlertType.MSG),
	/**
	 * 请至少保留两条分录！
	 */
	ONLY_CW_VOUCHER_TWOENTRY("请至少保留两条分录！", AlertType.MSG),
	/**
	 * 短信账户余额不足，是否立即充值?
	 */
	NO_SEND_MONEY("短信账户余额不足，是否立即充值?", AlertType.MSG),
	 /**
	 * 不允许配置报表项本身!
	 */
	NO_CW_REPORT_SET("不允许配置报表项本身!", AlertType.WARN),
	/**
	 * 不允许跨年查询!
	 */
	NO_CW_REPORT_SELECT("不允许跨年查询!", AlertType.WARN),
	/**
	 * ${content}借贷不平衡！
	 */
	NO_CW_ACCOUNT_BALANCE("${content}借贷不平衡!", AlertType.WARN),
	/**
	 * 已经没有下一张了！
	 */
	NO_CW_VOUCHER_MOREDOWN("已经没有下一张凭证了！", AlertType.MSG),
	/**
	 * 已经没有上一张了！
	 */
	NO_CW_VOUCHER_MOREUP("已经没有上一张凭证了！", AlertType.MSG),
	/**
	 * ${content}借方和贷款金额不能都为空。
	 */
	NOT_CW_ACCOUNT_ALLEMPTY("${content}借方和贷款金额不能都为空。", AlertType.MSG),
	/**
	 * 没有需要整理的凭证。
	 */
	NO_CW_VOUCHER_UP("没有需要整理的凭证。",AlertType.MSG),
	/**
	 * 恭喜你，凭证整理完成。
	 */
	COMPLETED_CW_VOUCHER_UP("恭喜你，凭证整理完成!",AlertType.MSG),
	/**
	 * 现金流量分析已禁用，请先到参数设置开启。
	 */
	NOT_CW_CASHFLOW_USE("现金流量分析已禁用，请先到参数设置开启",AlertType.MSG),
	/**
	 * 此项尚未配置现金流方向或属于系统固定统计项，请选择其它项。
	 */
	NO_CW_CASHFLLOW_SET("此项尚未配置现金流方向或属于系统固定统计项，请选择其它项",AlertType.MSG),
	/**
	 * 已结账期凭证无法反审核。
	 */
	NOT_CW_VOUCHER_APPROVE("已结账期凭证无法反审核。",AlertType.MSG),
	/**
	 * ${content}凭证已记账无法删除。
	 */
	NOT_CW_VOUCHER_DELETE("${content}凭证已记账无法删除。",AlertType.MSG),
	/**
	 * ${content}表外科目不能与表内科目出现在同一张凭证中
	 */
	NOT_CW_SUBJECT_APPEAR("${content}表外科目不能与表内科目出现在同一张凭证中。",AlertType.MSG),
	/**
	 * 此期已结账，无法再录入凭证!
	 */
	NOT_CW_ACCOUNT_SET("此期已结账，无法再录入凭证!",AlertType.MSG),
	
	/**
	 * ${money1}与${money2}不一致，请重新输入。
	 */
	NOT_MONEY_SAME("${money1}与${money2}不一致，请重新输入。", AlertType.WARN),
	
	/**
	 * ${accountNo}与合同中的账号不一致，是否继续?。
	 */
	NOT_ACCNO_SAME("${accountNo}不一致，是否继续?", AlertType.CONFIRM),
	/**
	 * ${accountNo}。
	 */
	NOT_ACCNO_SAME_WARN("${accountNo}不一致", AlertType.WARN),
	
	/**
	 * 判断是否允许提前还款
	 */
	NOT_ALLOW_REPAYMENT("${content1}不允许${content2}！", AlertType.WARN),
	
	/**
	 * 请检查月结处理是否正确，[资产=负债+所有者权益]不成立，无法完成结账处理。
	 */
	FAILED_CW_ACCOUNT_SET("请检查月结处理是否正确，[资产=负债+所有者权益]不成立，无法完成结账处理。",AlertType.MSG),
	/**
	 * 非明细科目不允许记账。
	 */
	NO_CW_VOUCHER_ACCOUNT("非明细科目不允许记账。",AlertType.MSG),
	/**
	 * 已被使用，请重新选择凭证字号。
	 */
	EXIST_CW_VOUCHER_USE("${content}已被使用，请重新选择凭证字号。",AlertType.MSG),
	/**
	 * ${content}分录序号只能输入正整数
	 */
	ONLY_CW_VOUCHER_NUMBER("${content}分录序号只能输入正整数。", AlertType.MSG),
	/**
	 * ${content}科目存在${type},请仔细检查模版
	 */
	EXIST_CW_ACCOUNR_SUBJECT("${content}科目存在${type},请仔细检查模版。",AlertType.MSG),
	/**
	 * ${content}借贷金额不能为0。
	 */
	NOT_CW_ACCOUNT_NUM("${content}借贷金额不能为0。",AlertType.MSG),
	/**
	 * 检查未知报表类别！
	 */
	FAILED_CW_REPORT_TYPE("检查未知报表类别！",AlertType.MSG),
	/**
	 * 超出报表范围，无法调整顺序。
	 */
	FAILED_CW_REPORT_SORT("超出报表范围，无法调整顺序。",AlertType.MSG),
	/**
	 * 报表项公式存在重复数据。
	 */
	EXIST_CW_REPORT_DATA("报表项公式存在重复数据。",AlertType.MSG),
	/**
	 * 当前卡片已生成凭证，请先删除凭证后再删除卡片。
	 */
	EXIST_CW_VOUCHER_DELETE("当前卡片已生成凭证，请先删除凭证后再删除卡片。",AlertType.MSG),
	/**
	 * 设置成功，仅对此之后的报表生效。
	 */
	ONLY_CW_REPORT_EFFECT("设置成功，仅对此之后的报表生效。",AlertType.MSG),
	/**
	 * 该项用于统计发生额，不可选择！
	 */
	ONLY_CW_ACCOUNT_COUNT("该项用于统计发生额，不可选择！", AlertType.MSG),
	/**
	 * 确定要返回凭证列表吗？
	 */
	CONFIRM_CW_ACCOUNT_WRITEOFF("确定要返回凭证列表吗？", AlertType.CONFIRM),
	/**
	 * 本期存在未分析凭证，是否继续生成报表？
	 */
	CONFIRM_CW_VOUCHER_WRITEOFF("本期存在未分析凭证，是否继续生成报表？", AlertType.CONFIRM),
	/**
	 * 已结账期凭证无法反审核！
	 */
	NOT_CW_VOUCHER_REVIEW("已结账期凭证无法反审核！", AlertType.WARN),
	/**
	 * 凭证金额不允许录入负数！
	 */
	NOT_CW_VOUCHER_NEGATIVE("凭证金额不允许录入负数！", AlertType.WARN),
	/**
	 * 您有工作正在托管，是否立即前往收回?
	 */
	CONFIRM_ACCREDIT_ALL("您有工作正在托管，是否立即前往收回?", AlertType.CONFIRM),
	/**
	 * 您已托管此功能，收回后才可进行操作，是否立即收回？
	 */
	CONFIRM_ACCREDIT_FUNC("您已托管此功能，收回后才可进行操作，是否立即收回？", AlertType.CONFIRM),
	/**
	 * 您登记的证件号码与${fieldOne}重复，是否完善客户信息？
	 */
	CONFIRM_UPDATE_CUSTOMER("您登记的证件号码与${fieldOne}重复", AlertType.CONFIRM),
	/**
	 * 是否立即收回托管？
	 */
	CONFIRM_ACCREDIT_TAKE("是否立即收回托管？", AlertType.CONFIRM),
	/**
	 * 确定要发回到申请阶段吗 ？
	 */
	CONFIRM_APPROVAL_RETURN_FIRST("确定要发回到申请阶段吗？", AlertType.CONFIRM),
	/**
	 * 确定要否决吗?
	 */
	CONFIRM_APPROVAL_REJECT("确定要否决吗?", AlertType.CONFIRM),
	/**
	 * 确定要退回上一环节吗？
	 */
	CONFIRM_BACK_PREV("确定要退回上一环节吗？", AlertType.CONFIRM),
	/**
	 * 此操作不可逆，请谨慎操作！确定要清理所有客户、业务、押品及归档信息吗？
	 */
	CONFIRM_CLEAN_ALL("此操作不可逆，请谨慎操作！确定要清理所有客户、业务、押品及归档信息吗？", AlertType.CONFIRM),
	/**
	 * 此操作不可逆，请谨慎操作！确定要清理客户${cusName}吗？
	 */
	CONFIRM_CLEAN_CUS("此操作不可逆，请谨慎操作！确定要清理客户${cusName}吗？", AlertType.CONFIRM),
	/**
	 * 此操作不可逆，请谨慎操作！确定要清理吗?
	 */
	CONFIRM_CLEAN("此操作不可逆，请谨慎操作！确定要清理吗？", AlertType.CONFIRM),
	/**
	 * 您还没有配置筛选条件，是否继续保存？?
	 */
	CONFIRM_FILTER_SAVE("您还没有配置筛选条件，是否继续保存？", AlertType.CONFIRM),
	/**
	 * 该客户的押品为项目${appName}提供担保，不支持清理
	 */
	ERROR_CLEAN_PLE_REL("该客户的押品为项目${appName}提供担保，不支持清理。", AlertType.ERROR),
	/**
	 * 该客户与${cusName}在项目${appName}中是合作关系，不支持清理
	 */
	ERROR_CLEAN_COOP_REL("该客户与${cusName}在项目${appName}中是合作关系，不支持清理。", AlertType.ERROR),
	/**
	 * 确定要提交吗？
	 */
	CONFIRM_COMMIT("确定要提交吗？", AlertType.CONFIRM),
	/**
	 * 确定要删除吗？
	 */
	CONFIRM_DELETE("确定要删除吗？", AlertType.CONFIRM),
	/**
	 * 和审批角色已关联确定要删除吗？
	 */
	CONFIRM_DELETE_ASSOCIATE("该操作员已被设置为审批用户，确定要删除吗？", AlertType.CONFIRM),
	/**
	 * 确定要放弃此操作吗？
	 */
	CONFIRM_HALT("确定要放弃此操作吗？", AlertType.CONFIRM),
	/**
	 * 本条短信包括${length}个字，按${num}条短信发送，确定要发送吗？
	 */
	CONFIRM_MSG_SEND("本条短信包括${length}个字，按${num}条短信发送，确定要发送吗？", AlertType.CONFIRM),
	/**
	 * 确定要进行${operation}操作吗？
	 */
	CONFIRM_OPERATION("确定要进行${operation}操作吗？", AlertType.CONFIRM),
	/**
	 * ${reason},确定要进行${operation}操作吗？
	 */
	CONFIRM_OPERATION_REASON("${reason},确定要进行${operation}操作吗？", AlertType.CONFIRM),
	/**
	 * 本次收费0.5元，确定要查询吗？
	 */
	CONFIRM_QUERY("本次收费0.5元，确定要查询吗？",AlertType.CONFIRM),
	/**
	 * 尚未录入报表数据,是否继续确认？
	 */
	CONFIRM_FIN_VERIFY("尚未录入报表数据,是否继续确认？", AlertType.CONFIRM),
	/**
	 * 请先添加财务报表并进行数据确认！
	 */
	FIRST_FINC_VERIFY("请先添加财务报表并进行数据确认！", AlertType.WARN),
	/**
	 * 数据确认失败！
	 */
	ERROR_FIN_VERIFY("数据确认失败！", AlertType.ERROR),
	/**
	 * 该报表已存在，是否选择覆盖？
	 */
	CONFIRM_FIN_REPEAT("该报表已存在，是否选择覆盖？", AlertType.CONFIRM),
	/**
	 * 该客户当前已有授信，是否进行调整授信？
	 */
	CONFIRM_FIN_ADJUST("该客户当前已有授信，是否进行调整授信？", AlertType.CONFIRM),
	/**
	 * 新增失败,报表日期不能重复!
	 */
	ERROR_FIN_DATE_REPEAT("新增失败,报表日期不能重复!", AlertType.ERROR),
	/**
	 * 操作失败！文件格式错误!
	 */
	ERROR_FIN_FILE_FORMAT("操作失败！文件格式错误!", AlertType.ERROR),
	/**
	 * 此操作不可逆转，请谨慎处理，确定要进行${operation}操作吗？
	 */
	CONFIRM_OPERATION_SERIOUS("此操作不可逆转，请谨慎处理，确定要进行${operation}操作吗？", AlertType.CONFIRM),
	/**
	 * 您登记的${customerOne}的${fieldOne}与以下信息重复：${customerTwo}的${fieldTwo}，${customerThree}的${fieldThree}，继续保存？
	 */
	CONFIRM_SAVE_CUSTOMER("您登记的${customerOne}的${fieldOne}与以下信息重复：${customerTwo}的${fieldTwo}，${customerThree}的${fieldThree}，继续保存？", AlertType.CONFIRM),
	/**
	 * 失败！
	 */
	ERROR("失败！", AlertType.ERROR),
	/**
	 * 服务器异常！请重启服务或联系软件提供商。
	 */
	ERROR_SERVER("服务器异常！请重启服务或联系软件提供商。", AlertType.ERROR),
	/**
	 * 调用前置条件检查规则失败！请联系软件提供商。
	 */
	ERROR_CHECK_PREFIX("调用前置条件检查规则失败！请联系软件提供商。", AlertType.ERROR),
	/**
	 * 系统中已有该客户类型的模型。
	 */
	ERROR_CREDIT_INSERT("系统中已有该客户类型的模型。", AlertType.ERROR),
	/**
	 * 系统中已经有该客户类型的模型。
	 */
	ERROR_CUSTYPE("系统中已经有该客户类型的模型。", AlertType.ERROR),
	/**
	 * 获取${content}信息异常。
	 */
	ERROR_DATA_CREDIT("获取${content}信息异常。", AlertType.ERROR),
	/**
	 * 请求${url}异常。
	 */
	ERROR_REQUEST_URL("请求${url}异常。", AlertType.ERROR),
	/**
	 * 文件名过长，请重新输入。
	 */
	ERROR_FILE_NAME_TOOLONG("文件名过长，请重新输入。", AlertType.MSG),
	/**
	 * 表单检查异常。
	 */
	ERROR_FORM_CHECK("表单检查异常。", AlertType.ERROR),
	/**
	 * ${field}格式输入有误。
	 */
	ERROR_FORM_FORMAT("${field}格式输入有误。", AlertType.ERROR),

	/**
	 * 长度大于${length}。
	 */
	ERROR_FORM_LENGTH("长度大于${length}。", AlertType.ERROR),
	/**
	 * 服务器发生异常，可能是数据丢失！请联系软件提供商。
	 */
	ERROR_NULL_POINT("服务器发生异常，可能是数据丢失！请联系软件提供商。", AlertType.MSG),
	/**
	 * 暂时没有可重置表单
	 */
	ERROR_NULL_FORM("暂时没有可重置表单", AlertType.MSG),
	/**
	 * 密码输入有误，请重新输入。
	 */
	ERROR_PASSWORD("密码输入有误，请重新输入。", AlertType.ERROR),
	/**
	 * 密码输入有误，请重新输入。
	 */
	ERROR_PASSWORD_OLD("旧密码输入有误，请重新输入。", AlertType.ERROR),
	/**
	 * 密码必须包含英文小写字母(a到 z)。
	 */
	ERROR_PASSWORD_FORMAT("密码必须包含英文小写字母(a到 z)。", AlertType.WARN),
	/**
	 * 押品编号重复！
	 */
	ERROR_PLEDGESHOWNO_REPETITION("押品编号重复！",AlertType.ERROR),
	/**
	 * 流程提交失败。
	 */
	ERROR_PROCESS_COMMIT("流程提交失败。", AlertType.ERROR),
	/**
	 * 新增失败。
	 */
	ERROR_INSERT("新增失败！", AlertType.TIP),
	/**
	 * 场景已被使用，不能删除！
	 */
	ERROR_SCENCE_EVAL("场景已被使用，不能删除！", AlertType.WARN),
	/**
	 * 查询失败。
	 */
	ERROR_SELECT("查询失败。", AlertType.ERROR),
	/**
	 * 数据库异常，请使用服务管理端重启服务后重试。如果问题依然存在，请联系软件客服人员。
	 */
	ERROR_SQL_INFO("数据库异常，请使用服务管理端重启服务后重试。如果问题依然存在，请联系软件提供商。", AlertType.ERROR),
	/**
	 * ${content}已存在，请重新输入！
	 */
	EXIST_INFORMATION_EVAL("${content}已存在，请重新输入！", AlertType.ERROR),
	/**
	 * ${pactNo}已被使用，请重新选择${followType}合同编号。
	 * followType参数传个“主”（可不传）/“从”。
	 */
	EXIST_PACT_NO("${pactNo}已被使用，请重新选择${followType}合同编号。", AlertType.WARN),
	/**
	 * ${productName}已有存量业务，不允许删除！
	 */
	EXIST_PRDCT_USING("${productName}已有存量业务，不允许删除！", AlertType.ERROR),
	/**
	 * 所选借据${loanNames}已存在指派的检查任务！
	 * loanNames - 项目名+借据号
	 */
	EXIST_EXAM_FINC("所选借据${loanNames}已存在指派的检查任务！", AlertType.WARN),
	/**
	 * 审批被否决！
	 */
	FAILD_APPROVAL_REJECT("审批被否决！", AlertType.CONFIRM),
	/**
	 * 刷新失败！
	 */
	FAILED_REFRESH("刷新失败！", AlertType.ERROR),
	/**
	 * 删除失败！
	 */
	FAILED_DELETE("删除失败！", AlertType.ERROR),
	/**
	 * 系统内置账户不允许删除或禁用！
	 */
	FAILED_DELETE_USER("系统内置账户不允许删除或禁用！", AlertType.ERROR),
	/**
	 * 下载文件出错！原因：${reason}
	 */
	FAILED_FILE_DOWNLOAD("下载文件出错！原因：${reason}", AlertType.ERROR),
	/**
	 * 调用第三方接口失败，此次查询不收费。
	 */
	FAILED_INTERFACE_CLOUD("调用第三方接口失败，此次查询不收费。", AlertType.ERROR),
	/**
	 * 短信息发送失败。
	 */
	FAILED_MSG_SEND("短信发送失败。", AlertType.MSG),
	/**
	 * ${operation}操作失败！
	 */
	FAILED_OPERATION("${operation}操作失败！", AlertType.ERROR),
	/**
	 * 保存失败！
	 */
	FAILED_SAVE("保存失败！", AlertType.ERROR),
	/**
	 * ${content}保存失败。失败原因：${reason}
	 */
	FAILED_SAVE_CONTENT("${content}保存失败。失败原因：${reason}", AlertType.MSG),
	/**
	 * 提交失败！
	 */
	FAILED_SUBMIT("提交失败！", AlertType.ERROR),
	/**
	 * 提交失败！失败原因：${reason}
	 */
	FAILED_SUBMIT_CONTENT("提交失败！失败原因：${reason}", AlertType.MSG),

	/**
	 * 自动评估失败！失败原因：${reason}
	 */
	FAILED_EVAL_CONTENT("自动评估车价失败！失败原因：${reason}", AlertType.MSG),
	/**
	 * 更新失败！
	 */
	FAILED_UPDATE("更新失败！", AlertType.ERROR),
	/**
	 * 该客户无法新增业务，请检查产品设置、操作员权限或部门。
	 */
	FIRST_CHECK_PRODUCT("该客户无法新增业务，请检查产品设置、操作员权限或部门。", AlertType.WARN),
	/**
	 * 请先进行${operation}。
	 */
	FIRST_OPERATION("请先进行${operation}。", AlertType.WARN),
	/**
	 * 请先添加${content}。
	 */
	FIRST_OPERATION_ADD("请先添加${content}。", AlertType.WARN),
	/**
	 * 请选择需要处理的数据。
	 */
	FIRST_SELECT_ITEM("请选择需要处理的数据。", AlertType.WARN),
	/**
	 * 请选择${field}。
	 */
	FIRST_SELECT_FIELD("请选择${field}", AlertType.WARN),
	
	/**
	 * ${field}未启用。
	 */
	NO_ENABLE("${field}未启用", AlertType.WARN),
	/**
	 * ${field}不完整。
	 */
	DATA_INCOMPLETE("${field}不完整!请检查！", AlertType.WARN),
	/**
	 * 接收人与移交人不能相同。
	 */
	ERROR_SAME_TRANSER("接收人与移交人不能相同。", AlertType.ERROR),
	/**
	 * 请完善${infoName}。
	 */
	FIRST_COMPLETE_INFORMAATION("请完善${infoName}。", AlertType.ERROR),
	/**
	 * 请先保存正在修改的数据，以免丢失！。
	 */
	FIRST_SAVE_EDITING("请先保存正在修改的数据，以免丢失！", AlertType.WARN),
	/**
	 * 上传${file}失败。
	 */
	FAILED_UPLOAD_FILE("上传${file}失败!", AlertType.ERROR),
	/**
	 * 请上传${fileTypes}格式的文件!
	 */
	ERROR_UPLOAD_FILE_TYPE("请上传${fileTypes}格式的文件!", AlertType.ERROR),
	/**
	 * ${content}重复！请重新输入！
	 */
	ERROR_REPEAT("${content}重复！请重新输入！", AlertType.ERROR),
	/**
	 * 您登记的${unValtitle} ${unVal}与 以下信息重复：<br>${msg}
	 */
	ERROR_INFO_REPEAT("您登记的${unValtitle} ${unVal}与 以下信息重复：<br>${msg}", AlertType.ERROR),
	/**
	 * 紧急联系电话和手机号码不能相同！
	 */
	ERROR_TEL_EMER_REPEAT("紧急联系电话和手机号码不能相同！",AlertType.ERROR),
	/**
	 * 会话失效,请重新登录系统!
	 */
	NO_VALID_SESSION("会话失效,请重新登录系统!", AlertType.ERROR),
	/**
	 * 该客户尚未进行等级评估，请评级后再发起授信申请。
	 */
	NO_AUTH_INFO("该客户尚未进行等级评估，请评级后再发起授信申请。", AlertType.MSG),
	/**
	 * 当前客户类型没有匹配的授信模型，请先配置授信模型。
	 */
	NO_CREDIT_MODEL("当前客户类型没有匹配的授信模型，请先配置授信模型。", AlertType.ERROR),
	/**
	 * 您所查询的数据不存在，请调整查询条件后重新查询。
	 */
	NO_DATA("您所查询的数据不存在，请调整查询条件后重新查询。", AlertType.MSG),
	/**
	 * ${fileName}不存在
	 */
	NO_FILE("${fileName}不存在", AlertType.ERROR),
	/**
	 * ${fileName}没有上传
	 */
	NO_UPLOAD("${fileName}没有上传", AlertType.ERROR),
	/**
	 * 没有找到可下载的文件！
	 */
	NO_FILE_DOWNLOAD("没有找到可下载的文件！",AlertType.ERROR),
	/**
	 * 手机号${phoneNo}不存在
	 */
	NO_PHONENO("手机号${phoneNo}不存在", AlertType.ERROR),
	/**
	 * 磁盘空间不足，请删除过期文件或联系管理员。
	 */
	NO_SPACE("磁盘空间不足，请删除过期文件或联系管理员。", AlertType.ERROR),

	/**
	 * 审批岗位没有配置审批人员。
	 */
	NO_CONFIG_APPROVAL_PERSON("审批岗位没有配置审批人员。", AlertType.ERROR),
	/**
	 * 没有权限查看该数据
	 */
	NO_UPDATE_OPERABLE("没有权限查看该数据", AlertType.ERROR),
	/**
	 * 该任务不存在，可能已被其他操作员办理，请勿重复操作。
	 */
	NO_TASK_EXIST("该任务不存在，可能已被其他操作员办理，请勿重复操作。", AlertType.MSG),
	/**
	 * 审批意见不能为空。
	 */
	NOT_APPROVAL_IDEA_EMPTY("审批意见不能为空。", AlertType.ERROR),
	/**
	 * 审批意见类型不能为空。
	 */
	NOT_APPROVAL_TYPE_EMPTY("审批意见类型不能为空。", AlertType.ERROR),
	/**
	 * 不能为空。
	 */
	NOT_EMPTY("不能为空。", AlertType.MSG),
	/**
	 * 当前文件格式不支持在线查看，请下载后查阅。
	 */
	NOT_FILE_TYPE("当前文件格式不支持在线查看，请下载后查阅。", AlertType.ERROR),
	/**
	 * 基础表单不可禁用。
	 */
	NOT_FORM_BASIS("基础表单不可禁用。", AlertType.TIP),

	/**
	 * ${time} 不能大于当前系统时间。
	 */
	NOT_FORM_BIG_TIME("${time} 不能大于当前系统时间。", AlertType.ERROR),
	/**
	 * ${time} 不能大于当前系统时间。
	 */
	NOT_FORM_BIG_YEAR("${time} 不能大于当前系统年份。", AlertType.ERROR),
	/**
	 * ${field}不能为空。
	 */
	NOT_FORM_EMPTY("${field}不能为空。", AlertType.MSG),
	/**
	 * ${field}不能为空,请在${where}中完善。
	 */
	NOT_EMPTY_CONTENT("${field}不能为空,请在${where}中完善。", AlertType.MSG),
	/**
	 * ${field}的长度不能大于${length}位。
	 */
	NOT_FORM_LENGTH_GT("${field}的长度不能大于${length}位。", AlertType.MSG),
	/**
	 * ${field}的长度不能小于${length}位。
	 */
	NOT_FORM_LENGTH_LT("${field}的长度不能小于${length}位。", AlertType.MSG),
	/**
	 * ${time} 不能大于当前系统时间。
	 */
	NOT_FORM_SMALL_TIME("${time} 不能小于当前系统时间。", AlertType.ERROR),
	/**
	 * ${time} 不能大于当前系统时间。
	 */
	NOT_FORM_SMALL_YEAR("${time} 不能小于当前系统年份。", AlertType.ERROR),
	/**
	 * ${timeOne}不能大于${timeTwo}。
	 */
	NOT_FORM_TIME("${timeOne}不能大于${timeTwo}。", AlertType.ERROR),
	/**
	 * ${timeOne}不能小于${timeTwo}。
	 */
	NOT_SMALL_TIME("${timeOne}不能小于${timeTwo}。", AlertType.ERROR),
	/**
	 * 编号不存在。
	 */
	NOT_NUMBER("编号不存在。", AlertType.ERROR),
	/**
	 * 两次输入的新密码不一致，请重新输入。
	 */
	NOT_PASSWORD_SAME("两次输入的新密码不一致，请重新输入。", AlertType.WARN),
	/**
	 * 上传文件大小不能超过${size}MB
	 */
	NOT_SIZE_FILE("上传文件大小不能超过${size}MB。", AlertType.ERROR),
	/**
	 * 新密码不能与旧密码相同，请重新输入。
	 */
	NOT_SYSUSER_PASSWORD("新密码不能与旧密码相同，请重新输入。", AlertType.ERROR),
	/**
	 * 不能为${type}
	 */
	NOT_TYPE("不能为${type}", AlertType.ERROR),
	/**
	 * 没有匹配到${name1}，请先配置${name2}。
	 * 例如：没有匹配到检查模型，请先配置检查模型。
	 */
	NO_MATCH_MODEL("没有匹配到${name1}，请先配置${name2}。", AlertType.ERROR),
	/**
	 * 默认展示的筛选项不能超过5个。
	 */
	NOT_FILTER_NUMBER("默认展示的筛选项不能超过5个", AlertType.ERROR),
	/**
	 * ${field}的长度应为${length}位。
	 */
	ONLY_FORM_LENGTH("${field}的长度应为${length}位。", AlertType.MSG),
	/**
	 * ${field}的长度应该在${lengthOne}位到${lengthTwo}位之间。
	 */
	ONLY_FORM_LENGTH_SCOPE("${field}的长度应该在${lengthOne}位到${lengthTwo}位之间。", AlertType.MSG),
	/**
	 * ${field}必须在${value1}到${value2}之间。
	 */
	ONLY_FORM_VALUE_SCOPE("${field}必须在${value1}到${value2}之间。", AlertType.ERROR),
	/**
	 * 根据${info}信息，${field}必须在${value1}到${value2}之间。
	 */
	ONLY_APPLY_VALUE_SCOPE("根据${info}信息，${field}必须在${value1}到${value2}之间。", AlertType.ERROR),
	/**
	 * 根据${info}信息，${field}不能大于${value}。
	 */
	NOT_APPLY_VALUE_BIG("根据${info}信息，${field}不能大于${value}。", AlertType.ERROR),
	/**
	 * 只能输入${type}
	 */
	ONLY_NUMBER("只能输入${type}", AlertType.MSG),
	/**
	 * 请上传规定格式的文件。
	 */
	ONLY_TYPE_FILE("请上传${type}格式的文件。", AlertType.MSG),

	/**
	 * 成功。
	 */
	SUCCEED("成功。", AlertType.MSG),
	/**
	 * 上传成功。
	 */
	SUCCEED_UPLOAD("上传成功。", AlertType.MSG),
	/**
	 * 申请已经再次提交，等待审批。
	 */
	SUCCEED_AGAIN_LEAVE("申请已经再次提交，等待审批。", AlertType.TIP),
	/**
	 * 提交至${userRole}岗位下的${opNo}进行审批。
	 */
	SUCCEED_APPROVAL("提交至${userRole}岗位下的${opNo}进行审批。", AlertType.MSG),
	/**
	 * @param approvalType	: 审批意见类型，如同意、否决。
	 * @param userRole		: 审批角色名。
	 * @param opNo			: 审批用户名。
	 * @消息内容 ${approvalType}！提交至${userRole}岗位下的${opNo}进行审批。
	 */
	SUCCEED_APPROVAL_AGREED("${approvalType}！提交至${userRole}岗位下的${opNo}进行审批。", AlertType.MSG),
	/**
	 * @param approvalType	: 审批意见类型，如同意、否决。
	 * @param userRole		: 审批角色名。
	 * @param opNo			: 审批用户名。
	 * @消息内容 ${approvalType}！提交至${userRole}岗位下的${opNo}进行补充资料。
	 */
	SUCCEED_APPROVAL_REMAND("${approvalType}！提交至${userRole}岗位下的${opNo}进行补充资料。", AlertType.MSG),
	/**
	 * 审批完成！业务流转到${node}。
	 */
	SUCCEED_APPROVAL_TONEXT("审批完成！业务流转到${node}。", AlertType.MSG),
	/**
	 * 提交成功！业务流转到${node}。
	 */
	SUCCEED_SUBMIT_TONEXT("提交成功！业务流转到${node}。", AlertType.MSG),
	/**
	 * 审批完成！
	 */
	SUCCEED_APPROVAL_FINISH("审批完成！", AlertType.MSG),
	/**
	 * ${flowName}审批完成！
	 */
	SUCCEED_APPROVAL_FINISH_CONTENT("${flowName}审批完成！", AlertType.MSG),

	/**
	 * 申请已经提交，等待审批。
	 */
	SUCCEED_APPROVAL_LEAVE("申请已经提交，等待审批。", AlertType.TIP),
	/**
	 * 已退回上一环节，发回至${userRole}岗位下的${opNo}进行审批。
	 */
	SUCCEED_APPROVAL_RETURN("已退回上一环节，发回至${userRole}岗位下的${opNo}进行审批。", AlertType.TIP),
	/**
	 * 发回到申请阶段！审批提交到初审岗位下的${opNo}进行审批。
	 */
	SUCCEED_BACK_FIRST_APPROVAL("发回到申请阶段！审批提交到初审岗位下的${opNo}进行审批。", AlertType.TIP),
	/**
	 * 加入流程成功。
	 */
	SUCCEED_INSERT_PROCESS("加入流程成功！", AlertType.TIP),
	/**
	 * 复核成功！业务流转到${node}。
	 */
	SUCCEED_REVIEW_TONEXT("复核成功！业务流转到${node}。", AlertType.MSG),
	/**
	 * 还款成功！业务流转到${node}。
	 */
	SUCCEED_REPAY_TONEXT("还款成功！业务流转到${node}。", AlertType.MSG),
	/**
	 * 删除成功。
	 */
	SUCCEED_DELETE("删除成功。", AlertType.TIP),
	/**
	 * 新增成功。
	 */
	SUCCEED_INSERT("新增成功。", AlertType.TIP),
	/**
	 * 提交成功。
	 */
	SUCCEED_COMMIT("提交成功。", AlertType.TIP),
	/**
	 * 短信息发送成功
	 */
	SUCCEED_MSG_SEND("短信发送成功。", AlertType.TIP),
	/**
	 * 刷新成功！
	 */
	SUCCEED_REFRESH("刷新成功！", AlertType.TIP),
	/**
	 * ${operation}操作成功。
	 */
	SUCCEED_OPERATION("${operation}操作成功。", AlertType.TIP),
	/**
	 * ${operation}已撤销。
	 */
	SUCCEED_OPERATION_UNDO("${operation}已撤销。", AlertType.TIP),
	/**
	 * 保存成功。
	 */
	SUCCEED_SAVE("保存成功。", AlertType.TIP),
	/**
	 * ${content}保存成功。
	 */
	SUCCEED_SAVE_CONTENT("${content}保存成功。", AlertType.TIP),
	/**
	 * 查询成功。
	 */
	SUCCEED_QUERY("查询成功。", AlertType.TIP),
	/**
	 * ${content}保存成功，请重新登录。
	 */
	SUCCEED_SAVE_LOGIN("${content}保存成功，请重新登录。", AlertType.TIP),
	/**
	 * 密码重置成功。
	 */
	SUCCEED_SYSUSER_PASSWORD("密码重置成功。", AlertType.TIP),
	/**
	 * 更新成功。
	 */
	SUCCEED_UPDATE("更新成功。", AlertType.TIP),
	/**
	 * 正在下载！
	 */
	SUCCEED_UPLOAND("正在下载！", AlertType.TIP),
	/**
	 * 押品已全部入库！业务直接提交到${node} 
	 */
	SUCCEED_INSTOCKBUSS("押品已全部入库！业务直接提交到 ${node}",AlertType.TIP),
	/**
	 * 押品已全部入库！
	 */
	SUCCEED_INSTOCKALL("押品已全部入库！",AlertType.TIP),
	/**
	 * 押品已经全部出库！
	 */
	SUCCEED_OUTSTOCKALL("押品已经全部出库！",AlertType.TIP),
	/**
	 * 确认解除担保关联吗？
	 */
	CONFIRM_DELCOLLAERTALREL("确认解除担保关联吗？",AlertType.TIP),
	/**
	 * 解除成功！
	 */
	SUCCEED_DELCOLLAERTALREL("解除成功！",AlertType.TIP),
	/**
	 * 暂无担保信息!
	 */
	NO_COLLATERAL("暂无担保信息!",AlertType.TIP),
	/**
	 * 置换成功！
	 */
	SUCCEED_REPLACE("置换成功！",AlertType.TIP),
	/**
	 * 争议解除成功！
	 */
	SUCCEED_RELIEVE_DISPUTED("争议解除成功！",AlertType.TIP),
	/**
	 * 折让确认成功！
	 */
	SUCCEED_REBATE_AFFIRM("折让确认成功！",AlertType.TIP),
	/**
	 * 反转让确认成功！
	 */
	SUCCEED_REPO_AFFIRM("反转让确认成功！",AlertType.TIP),
	/**
	 * 核查一次收费${cost}元，当前余额${balance}元，确认继续查询？
	 */
	CONFIRM_VERIFICATION_COST("核查一次收费${cost}元，当前余额${balance}元，确认继续查询？", AlertType.CONFIRM),
	/**
	 * 核查一次收费${cost}元，确认继续查询？
	 */
	CONFIRM_QUERY_COST("查询一次收费${cost}元，确认继续查询？", AlertType.CONFIRM),
	/**
	 * 评估成功！
	 */
	SUCCEED_ASSESSMETN("评估成功！${content}",AlertType.TIP),
	/**
	 * @param approvalType	: 审批意见类型，如同意、否决。
	 * @param userRole		: 审批角色和人员。
	 * @param 具体的操作		: 如：进行审批、补充资料。
	 * @消息内容 ${approvalType}！提交至${userRole}进行${operate}。
	 */
	SUCCEED_APPROVAL_COMMON("${approvalType}提交至${userRole}进行${operate}。", AlertType.MSG),
	/**
	 * ${content},确定要进行${operation}操作吗？
	 */
	CONFIRM_DETAIL_OPERATION("${content}，确定要进行${operation}操作吗？", AlertType.CONFIRM);
	
	/**
	 * 替换参数的正则。<br>
	 * <strong>如果正则或相应的匹配算法变动，需要相应的调整messageEnum.jsp的算法。</strong>
	 */
	public static final Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");

	/**
	 * 解析Enum，生成前台json格式字符串。
	 * @return
	 */
	public static String getObjStrForJS(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("{");
		for (MessageEnum enum1 : MessageEnum.values()) {
			/*
			 * 单个消息模板的结构如下：
			"CONFIRM_COMMIT" :{
				"id" : id,
				"message" : message,
				"alertType" : alertType
			};
			 */
			stringBuffer.append(", ");
			stringBuffer.append("\"" + enum1.name() + "\" : ");
			stringBuffer.append("{");
			stringBuffer.append("\"id\" : " + enum1.ordinal());
			stringBuffer.append(",");
			stringBuffer.append("\"message\" : \"" + enum1.message + "\"");
			stringBuffer.append(",");
			stringBuffer.append("\"alertType\" : " + enum1.alertType);
			stringBuffer.append("}");
		}
		// 替换第2个字符即首个逗号","。
		stringBuffer.replace(1, 2, "");
		stringBuffer.append("}");
		return stringBuffer.toString();
	}

	/* 枚举类的属性 */
	private int alertType;
	private String message;

	/**
	 * 私有构造方法①，不指定消息显示类型。
	 * 
	 * @param code
	 * @param message
	 * @param alertType
	 */
	private MessageEnum(String message) {
		this.message = message;
		this.alertType = AlertType.NONE.getValue();
	}

	/**
	 * 私有构造方法②，可以指定消息显示类型。
	 * 
	 * @param code
	 * @param message
	 * @param alertType
	 */
	private MessageEnum(String message, AlertType alertTypeEnum) {
		this.message = message;
		this.alertType = alertTypeEnum.getValue();
	}

	/**
	 * 无参调用。
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.name() + "：" + pattern.matcher(message).replaceAll("");
	}
	
	/**
	 * 有参调用，map内的参数请参考Enum实例的注释。
	 * 
	 * @param paramMap
	 * @return
	 */
	public String getMessage(CharSequence onlyParam) {
		if (onlyParam != null) {
			Matcher matcher = pattern.matcher(this.message);
			StringBuffer sbuffer = new StringBuffer();

			while (matcher.find()) {
				matcher.appendReplacement(sbuffer, onlyParam.toString());
			}

			matcher.appendTail(sbuffer);

			return this.name() + "：" + sbuffer.toString();
		}
		return this.name() + "：" + this.getMessage();
	}

	/**
	 * 有参调用，map内的参数请参考Enum实例的注释。
	 * 
	 * @param paramMap
	 * @return
	 */
	public String getMessage(Map<String, ? extends Object> paramMap) {
		if (paramMap != null && !paramMap.isEmpty()) {
			Matcher matcher = pattern.matcher(this.message);
			StringBuffer sbuffer = new StringBuffer();

			while (matcher.find()) {
				if (matcher.groupCount() > 0) {
					String paramKey = matcher.group(1); // ${xx}
					Object value = paramMap.get(paramKey);
					matcher.appendReplacement(sbuffer,
							value == null ? "" : value.toString());
				}
			}

			matcher.appendTail(sbuffer);

			return this.name() + "：" + sbuffer.toString();
		}
		return this.name() + "：" + this.getMessage();
	}

	@Override
	public String toString() {
		return this.name() + ":" + this.message;
	}
	
	/**
	 * 弹窗类型，与前台dthemes\factor\js\dialog.js对应。
	 * 0-error;1-自动消失的提示;2-确定/取消，询问类;3-提示信息，不消失;4-警告类，warn。
	 * 
	 * @author LiuYF
	 * 
	 */
	enum AlertType {
		/**
		 * 没有指定的消息展示类型（无需弹窗）
		 */
		NONE(-1),
		/**
		 * 错误提示。
		 */
		ERROR(0),
		/**
		 * 自动消失的提示信息。
		 */
		TIP(1),
		/**
		 * 确认类信息。
		 */
		CONFIRM(2),
		/**
		 * 提示信息，不自动消失
		 */
		MSG(3),
		/**
		 * 警告类信息。
		 */
		WARN(4);

		/**
		 * 根据传入的值获取枚举实例。
		 * @param alertType
		 * @return
		 */
		public static AlertType getByValue(int alertType) {
			for (AlertType atEnum : AlertType.values()) {
				if (alertType == atEnum.value) {
					return atEnum;
				}
			}
			return null;
		}

		private int value;

		private AlertType(int typeValue) {
			this.value = typeValue;
		}

		public int getValue() {
			return value;
		}
	}

	public static void main(String... s) {
		System.out.println(MessageEnum.CONFIRM_COMMIT.getMessage());
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("phoneNo", "158356561235");
		System.out.println(MessageEnum.NO_PHONENO.getMessage(paramMap));
		System.out.println(MessageEnum.NO_PHONENO.getMessage("17989890890"));
		System.out.println(MessageEnum.SUCCEED_OPERATION.getMessage("提交审批"));
		MessageEnum.NO_FILE.getMessage("该评级指标");
		System.out.println(MessageEnum.values().length + " 个消息模板");
		System.out.println(getObjStrForJS());
	}
}
