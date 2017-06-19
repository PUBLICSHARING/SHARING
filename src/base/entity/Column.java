package base.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	//中文名称
	public String label();
	//最小长度
	public int minLength() default 0;
	//最大长度
	public int maxLength() default 20;
	//最小值
	public int minValue() default 0;
	//最大值
	public int maxValue() default 100;
	//是否可以为空
	public boolean isNullable() default true;
	//是否唯一
	public boolean isUnique() default false;
	//是否为组合关系
	public boolean isCombination() default false;
	//数据库中代替字段
	public String attribute() default "null";
}
