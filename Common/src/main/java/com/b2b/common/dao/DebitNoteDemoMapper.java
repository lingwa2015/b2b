package com.b2b.common.dao;

import com.b2b.common.domain.DebitNoteDemo;



























import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DebitNoteDemoMapper {
    int countByDebitNoteSql(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectDebitNoteByOrderDate(HashMap<String, Object> paramMap);
    
    int countByInvoiceSql(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectInvoiceByOrderDate(HashMap<String, Object> paramMap);
    
    int countByPreferentialSql(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectPreferentialByOrderDate(HashMap<String, Object> paramMap);
    
    int countBySumSql(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectSumByOrderDate(HashMap<String, Object> paramMap);
    
    int countPageGroup(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectSumPageGroup(HashMap<String, Object> paramMap);
    
    List<DebitNoteDemo> selectByUserId(HashMap<String, Object> paramMap);

	DebitNoteDemo findTotal(@Param("userName")String userName,@Param("startTime")Date starttime,@Param("endTime") Date endtime, @Param("regionId")Integer regionId, @Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId, @Param("type")String type, @Param("debflag")String debflag, @Param("invflag")String invflag);

	Long totalPreferential(@Param("userName")String userName, @Param("type")String type, @Param("starttime")Date starttime,
			@Param("querytime")Date querytime, @Param("crestdate")Date crestdate, @Param("creenddate")Date creenddate, @Param("cityId")Integer cityId);

	List<DebitNoteDemo> findReceivedPaymentsReport(@Param("type")String type, @Param("cityId")Integer cityId);

	List<DebitNoteDemo> findReceivedPaymentsDetailReport(@Param("type")String type,
			@Param("queryDate")String queryDate, @Param("cityId")Integer cityId);

	Long totaldebitNote(@Param("userName")String userName, @Param("starttime")Date starttime, @Param("querytime")Date querytime,
			@Param("crestdate")Date crestdate, @Param("creenddate")Date creenddate, @Param("cityId")Integer cityId);

	Long totalInvoice(@Param("userName")String userName, @Param("starttime")Date starttime, @Param("querytime")Date querytime,
			@Param("crestdate")Date crestdate, @Param("creenddate")Date creenddate, @Param("cityId")Integer cityId);

	DebitNoteDemo findTotalaaa(@Param("userName")String userName,@Param("startTime")Date starttime,@Param("endTime") Date endtime, @Param("regionId")Integer regionId,@Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId, @Param("type")String type, @Param("debflag")String debflag, @Param("invflag")String invflag);

	Long findBeforeTimeNotVerificationInvoiceFeeByShopId(@Param("shopId")Integer shopId);

	Integer findHasVerification(@Param("shopId")Integer shopId);

	DebitNoteDemo findDebitCusNumAndFeeByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	DebitNoteDemo findInvoiceCusNumAndFeeByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	DebitNoteDemo findInvoAgeByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	DebitNoteDemo findDebitAgeByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	DebitNoteDemo findInvoiceListByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	DebitNoteDemo findDebitListByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	List<DebitNoteDemo> findInvoicesByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	List<DebitNoteDemo> findDebitsByCityIdAndReseauId(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	List<DebitNoteDemo> findDebitagesByCityIdAndReseauIdAndflag(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId, @Param("flag")Integer flag,
			@Param("param")Integer param);

	List<DebitNoteDemo> findInvoiceagesByCityIdAndReseauIdAndflag(@Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId, @Param("flag")Integer flag,
			@Param("param")Integer param);

	Long findMoneyByDateAndCityId(@Param("date")Date date,@Param("cityId")Integer cityId);

	Long findMoneyByDateAndCityIdAndReseauId(@Param("date")Date date, @Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);
	
}