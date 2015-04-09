package com.heymilo.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.IteratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.heymilo.common.CommonUtils;
import com.heymilo.common.FileService;
import com.heymilo.common.ImageInfo;
import com.heymilo.identity.ILogin;
import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.master.CategoryService;
import com.heymilo.shop.master.ExhibitionService;
import com.heymilo.shop.master.ProductFeatureService;
import com.heymilo.shop.product.ProductService;
import com.heymilo.ui.param.ProductImageModel;
import com.heymilo.ui.param.ProductSaveModel;
import com.heymilo.ui.param.ProductSearchModel;

@Controller
public class AdminProductController {
	@Autowired
	private ILogin login;	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductFeatureService featureService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/admin/listProducts", method = RequestMethod.GET)
	public String listProducts() throws IOException {
		return "admin/product/listProducts";
	}
	
	@RequestMapping(value = "/admin/searchProducts", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchProducts(@ModelAttribute ProductSearchModel condition, BindingResult bindingResult) throws IOException{
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		Category category = null;
		if(condition.getCategoryId() != null){
			category = categoryService.loadCategory(condition.getCategoryId());
		}
		
		List<Product> results = productService.searchProduct(condition.getKeyword(), condition.getNewProduct(), 
				condition.getCanSubscription(), condition.getStatus(), category, condition.getStart(), condition.getLimit()); 
		
		int totalCount = productService.countProduct(condition.getKeyword(), condition.getNewProduct(), 
				condition.getCanSubscription(), condition.getStatus(), category);
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
	
	@RequestMapping(value = "/admin/productForm", method = RequestMethod.GET)
	public String productForm(@RequestParam(value="id",required=false) Long productId, Model model) throws IOException {
		int stockCount = 0;
		if(productId != null){
			Product product = productService.load(productId);
			model.addAttribute("product", product);
			stockCount = product.getTotalCount() - product.getSalesCount();
		}
		 
		
		List<ProductFeature> features = featureService.search(null, 0, 15);
		List<Category> categories = categoryService.listCategories(0, 15);
		List<Exhibition> exhibitions = exhibitionService.search(null, null, 0, 15);
		
		model.addAttribute("categories",categories);
		model.addAttribute("features", features);
		model.addAttribute("exhibitions", exhibitions);
		model.addAttribute("stockCount", stockCount);
		
		return "admin/product/productForm";
	}
	
	@RequestMapping(value = "/admin/uploadImage/main", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam("mainImageUpload") MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
		ImageInfo info = fileService.updateImage(multipartFile, "product");
		return CommonUtils.toJson(info);
	}
	
	@RequestMapping(value = "/admin/uploadImage/sub/{idx}", method = RequestMethod.POST)
	@ResponseBody
	public String uploadSubImage(@PathVariable("idx") Integer idx, @RequestParam("subImageUpload") MultipartFile[] multipartFiles,
			HttpServletRequest request) throws IOException{
		MultipartFile file = multipartFiles[idx];
		
		ImageInfo info = saveImage(file);
		return CommonUtils.toJson(info);
	}
	
	private ImageInfo saveImage(MultipartFile multipartFile) throws IOException {
		return fileService.updateImage(multipartFile, "product");
	}
	
	@RequestMapping(value = "/admin/saveProduct",  method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute ProductSaveModel productSaveModel, BindingResult bidingResult) throws IOException {
		
		Product product = productService.saveProduct(productSaveModel);
		return "redirect:/admin/productForm?id=" + product.getId();
	}
	
}
