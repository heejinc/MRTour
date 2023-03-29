package com.mrtour.view.product;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mrtour.model.product.ProductInfoVO;
import com.mrtour.model.product.ProductService;
import com.mrtour.home.Pager;
import com.mrtour.model.member.MemberService;
import com.mrtour.model.member.MemberVO;
import com.mrtour.model.payment.PaymentVO;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	// 호텔 메인페이지
	@RequestMapping("/hotel_main")
	public String hotel_main() {return "products/hotel_main";}
	
	
	// 호텔 상세페이지
	@RequestMapping("/hotel_page")
	public String hotel_page() {return "products/hotel_page";}
	
	
	// 호텔 결제페이지
	@RequestMapping("/hotel_checkout")
	public String hotel_checkout() {return "products/hotel_checkout";}
		
	
	// 티켓 메인페이지
	@RequestMapping("/ticket_main")
	public String ticket_main() {return "products/ticket_main";}
	
	
	// 티켓 상세페이지
	@RequestMapping("/ticket_page")
	public String ticket_page() {return "products/ticket_page";}
	
	
	// 렌트카 상세페이지(렌트카는 메인페이지 없음)
	@RequestMapping("/car_page")
	public String car_page() {return "products/car_page";}
	
	// 렌트카 예약페이지
	@RequestMapping("/car_checkout")
	public String car_checkout() {return "products/car_checkout";}
	
	
	
	
	
	
	
	//관리자 상품관리
	
	// 관리자 상품관리 화면으로 가기
	@RequestMapping("/admin_insertProduct")
	public String InsertProduct() {
		return "admin/insertProduct";
	}
	
	// 상품목록 페이지
		@RequestMapping("/Listpage")
		public String ListPage(ProductInfoVO vo) {
			return "admin/productList";
		}
		
	// 상품코드 중복검사
	@ResponseBody
	@RequestMapping(value = "/prdCheckID")
	public int prdCheckID(ProductInfoVO vo) {
		int prdCheckID = productService.prdCheckID(vo);
		return prdCheckID;
	}
	
	// (진) 상품등록
		@RequestMapping(value = "/insertProduct")
		public String insertProduct(MultipartHttpServletRequest multi, ProductInfoVO vo) {
			System.out.println(vo.toString());
			String root = "../stcwk/MRTourWeb/src/main/webapp/";
			String path = "resources/img/product/" + vo.getCate_id() + "/";
			String realpath = root + "resources/img/product/" + vo.getCate_id() + "/";

			File dir = new File(realpath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			Iterator<String> files = multi.getFileNames();
			while (files.hasNext()) {
				String uploadFile = files.next();

				MultipartFile mFile = multi.getFile(uploadFile);
				String fileName = mFile.getOriginalFilename();
				vo.setPrd_img(path + fileName);
				
				try {
					mFile.transferTo(new File(realpath + fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			productService.insertProduct(vo);
			return "main";
		}
}