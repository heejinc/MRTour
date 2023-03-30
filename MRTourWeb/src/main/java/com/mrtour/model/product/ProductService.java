package com.mrtour.model.product;

import java.util.List;

public interface ProductService {

	// 관리자
	int prdCheckID(ProductInfoVO vo);

	// 물품 검색
	List<ProductInfoVO> listSearchPrd(String searchOption, int start, int end);

	int countSearchPrd(String searchOption, String prd_opt);

	// 물품 등록
	void insertProduct(ProductInfoVO vo);

	// 물품 리스트
	List<ProductInfoVO> getProductList(int start, int end, ProductInfoVO vo);

	List<ProductInfoVO> getAdminProductList(int start, int end, ProductInfoVO vo);

	int getCountProduct(ProductInfoVO vo);

	int getAdminCountProduct(ProductInfoVO vo);

	ProductInfoVO productDetail(ProductInfoVO vo);
}