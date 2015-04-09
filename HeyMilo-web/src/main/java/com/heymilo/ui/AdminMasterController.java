package com.heymilo.ui;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heymilo.common.CommonUtils;
import com.heymilo.identity.ILogin;
import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;
import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.master.CategoryService;
import com.heymilo.shop.master.ExhibitionService;
import com.heymilo.shop.master.ProductFeatureService;
import com.heymilo.shop.master.dto.CategoryDTO;
import com.heymilo.shop.master.dto.ExhibitionDTO;
import com.heymilo.shop.master.dto.ProductFeatureDTO;
import com.heymilo.ui.param.DataTableSearchModel;
import com.heymilo.ui.param.ExhibitionSearchModel;


@Controller
@RequestMapping("/admin")
public class AdminMasterController {
	@Autowired
	private ILogin login;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private ProductFeatureService featureService;
	
	@RequestMapping("/home")
	public String home() {
		
		return "admin/home";
	}
	
	@RequestMapping(value = "/listProductCategories", method = RequestMethod.GET)
	public String listProductCategories() throws IOException {
		
		return "admin/master/listProductCategories";
	}
	
	@RequestMapping(value = "/loadCategory",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loadCategory(@RequestParam("id") Long id) throws IOException {
		Category category = categoryService.loadCategory(id);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, category);
	}
	
	@RequestMapping(value = "/saveCategory",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveCategory(@ModelAttribute CategoryDTO dto, BindingResult result) throws IOException {
		categoryService.saveCategory(dto);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, null);
	}
	
	@RequestMapping(value = "/searchCategory", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchCategory(@ModelAttribute DataTableSearchModel condition, BindingResult result) throws IOException {
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		int totalCount = categoryService.countCategory();
		List<Category> results = categoryService.listCategories(condition.getStart(), condition.getLimit());
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
	
	@RequestMapping(value = "/listExhibitions", method = RequestMethod.GET)
	public String listExhibitions() throws IOException {
		
		return "admin/master/listExhibitions";
	}
	
	@RequestMapping(value = "/loadExhibition",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loadExhibition(@RequestParam("id") Long id) throws IOException {
		Exhibition exhibition = exhibitionService.load(id);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, exhibition);
	}
	
	@RequestMapping(value = "/saveExhibition",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String exhibition(@ModelAttribute ExhibitionDTO dto, BindingResult result) throws IOException {
		exhibitionService.save(dto);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, null);
	}
	
	@RequestMapping(value = "/searchExhibition", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchExhibition(@ModelAttribute ExhibitionSearchModel condition, BindingResult result) throws IOException {
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		int totalCount = exhibitionService.countSearch(condition.getKeyword(), condition.getType() == null ? null : ExhibitionType.valueOf(condition.getType()));
		List<Exhibition> results = exhibitionService.search(condition.getKeyword(), condition.getType() == null ? null : ExhibitionType.valueOf(condition.getType()),
				condition.getStart(), condition.getLimit());
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
	
	@RequestMapping(value = "/listFeatures", method = RequestMethod.GET)
	public String listFeatures() throws IOException {
		
		return "admin/master/listFeatures";
	}
	
	@RequestMapping(value = "/loadFeature",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loadFeature(@RequestParam("id") Long id) throws IOException {
		ProductFeature feature = featureService.load(id);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, feature);
	}
	
	@RequestMapping(value = "/saveFeature",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveFeature(@ModelAttribute ProductFeatureDTO dto, BindingResult result) throws IOException {
		featureService.save(dto);
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, null);
	}
	
	@RequestMapping(value = "/searchFeature", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchFeature(@ModelAttribute ExhibitionSearchModel condition, BindingResult result) throws IOException {
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		int totalCount = featureService.countSearch(condition.getKeyword());
		List<ProductFeature> results = featureService.search(condition.getKeyword(), condition.getStart(), condition.getLimit());
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
	
}
