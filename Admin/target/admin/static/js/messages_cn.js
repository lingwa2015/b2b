/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "输入不能为空",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的URL",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入数字（可以有小数）",
		digits: "请输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("输入长度不得大于 {0} "),
		minlength: jQuery.validator.format("输入长度不得小于 {0} "),
		rangelength: jQuery.validator.format("输入长度介于 {0} 和 {1} 之间"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});