package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.b2b.common.domain.DebitNote;
import com.b2b.common.domain.DebitNoteDemo;
import com.b2b.common.domain.Invoice;
import com.b2b.common.domain.Preferential;
import com.b2b.page.Page;

/**
 * 收款单
 * */
public interface DebitNoteService {

	public void create(DebitNote dto);
	
	public int delete(DebitNote dto,String userId);
	
	public DebitNote findById(String id);
	
	public void createInvoice(Invoice dto);
	
	public int deleteInvoice(Invoice dto,String userId);
	
	public Invoice findByIdInvoice(String id);
	
	public void createPreferential(Preferential dto);
	
	public int deletePreferential(Preferential dto,String userId);
	
	public Preferential findByIdPreferential(String id);
	
	public Page<DebitNoteDemo> debitNoteList(String userName, int currentPage ,int pageSize, Date starttime, Date querytime, Date crestdate, Date creenddate, Integer cityId);
	
	public List<DebitNoteDemo> debitNoteListExport(String userName);
	
	public List<DebitNoteDemo> debitNoteAmtListExport(String userName);
	
	public Page<DebitNoteDemo> debitInvoiceList(String userName, int currentPage ,int pageSize, Date starttime, Date querytime, Date crestdate, Date creenddate, Integer cityId);
	
	public List<DebitNoteDemo> debitInvoiceListExport(String userName);
	
	public Page<DebitNoteDemo> debitPreferentialList(String userName, int currentPage ,int pageSize, String type, Date starttime, Date querytime, Date crestdate, Date creenddate, Integer cityId);
	
	public List<DebitNoteDemo> debitPreferentialListExport(String userName);
	
	//public Page<Pair<DebitNoteDemo, List<DebitNoteDemo>>> debitNoteAmtListPage(String userName, Date startTime, Date endTime, int currentPage ,int pageSize);
	
	public Page<DebitNoteDemo> debitNoteAmtListPage(String userName, Date querytime, int currentPage ,String param,int pageSize);

	public DebitNoteDemo findTotal(String userName, Date startTime, Date querytime, Integer regionId,Integer reseauId, Integer cityId, String type, String debflag,String invflag);

	public List<DebitNoteDemo> selectByUserId(HashMap<String, Object> paramMapId);

	public List<DebitNoteDemo> debitNoteAmtList(String userName,
			Date starttime, Date querytime, String param, Integer regionId, Integer reseauId, Integer cityId, String type, String debflag,String invflag);

	public Long totalPreferential(String userName, String type, Date starttime,
			Date querytime, Date crestdate, Date creenddate, Integer cityId);

	public List<DebitNoteDemo> findReceivedPaymentsReport(String type, Integer cityId);

	public List<DebitNoteDemo> findReceivedPaymentsDetailReport(String type,
			String queryDate, Integer cityId);

	public Long totaldebitNote(String userName, Date starttime, Date querytime,
			Date crestdate, Date creenddate, Integer cityId);

	public Long totalInvoice(String userName, Date starttime, Date querytime,
			Date crestdate, Date creenddate, Integer cityId);

	public DebitNoteDemo findTotalaaa(String userName, Date starttime,
			Date querytime, Integer regionId, Integer reseauId, Integer cityId, String type, String debflag,String invflag);

	public Long findBeforeTimeNotVerificationInvoiceFeeByShopId(Integer shopId);

	public Integer findHasVerification(Integer shopId);
	
	/**
	 * 待回款客户数，待回款金额
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findDebitCusNumAndFeeByCityIdAndReseauId(Integer cityId, Integer reseauId);

	/**
	 * 已开票待回款客户数、已开票待回款总金额
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findInvoiceCusNumAndFeeByCityIdAndReseauId(Integer cityId, Integer reseauId);

	/**
	 * 票龄
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findInvoAgeByCityIdAndReseauId(Integer cityId, Integer reseauId);

	/**
	 * 账龄
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findDebitAgeByCityIdAndReseauId(Integer cityId, Integer reseauId);
	
	/**
	 * 最近开票
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findInvoiceListByCityIdAndReseauId(Integer cityId, Integer reseauId);
	
	/**
	 * 最近收款
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public DebitNoteDemo findDebitListByCityIdAndReseauId(Integer cityId, Integer reseauId);
	
	/**
	 * 开票信息
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public List<DebitNoteDemo> findInvoicesByCityIdAndReseauId(Integer cityId, Integer reseauId);
	
	/**
	 * 收款信息
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public List<DebitNoteDemo> findDebitsByCityIdAndReseauId(Integer cityId, Integer reseauId);
	
	/**
	 * 账龄列表
	 * @param cityId
	 * @param reseauId
	 * @param flag 账龄条件
	 * @param param 排序
	 * @return
	 */
	public List<DebitNoteDemo> findDebitagesByCityIdAndReseauIdAndflag(Integer cityId, Integer reseauId, Integer flag,
			Integer param);
	
	/**
	 * 票龄列表
	 * @param cityId
	 * @param reseauId
	 * @param flag
	 * @param param
	 * @return
	 */
	public List<DebitNoteDemo> findInvoiceagesByCityIdAndReseauIdAndflag(Integer cityId, Integer reseauId, Integer flag,
			Integer param);
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public Long findMoneyByDateAndCityId(Date date,Integer cityId);

	public Long findMoneyByDateAndCityIdAndReseauId(Date date, Integer cityId, Integer reseauId);
}
