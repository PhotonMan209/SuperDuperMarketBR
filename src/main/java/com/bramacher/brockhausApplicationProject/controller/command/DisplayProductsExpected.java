package com.bramacher.brockhausApplicationProject.controller.command;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.QualityDegradationRuleEntity;
import com.bramacher.brockhausApplicationProject.service.database.ProductService;
import com.bramacher.brockhausApplicationProject.service.database.QualityDegradationService;
import com.bramacher.brockhausApplicationProject.view.display.EntityDisplay;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DisplayProductsExpected implements CommandInterface {

    private final ProductService productService;
    private final QualityDegradationService qualityDegradationService;

    public DisplayProductsExpected(ProductService productService, QualityDegradationService qualityDegradationService) {
        this.productService = productService;
        this.qualityDegradationService = qualityDegradationService;
    }

    @Override
    public void execute(String[] args) {
        int days = 7; // Default to 7. 1 Week should be fitting for this use case
        if (args.length > 0) {
            try {
                days = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please provide a valid number of days.");
                return;
            }
        }

        System.out.println("Today");
        List<ProductEntity> productEntityList = productService.getAllProducts();
        EntityDisplay.displayEntity(productEntityList);

        Map<ProductEntity, QualityDegradationRuleEntity> productMap = productEntityList.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        p -> qualityDegradationService.getByProductEntityName(p.getProductTypeEntity().getName())
                ));

        for (int i = 1; i <= days; i++) {
            System.out.println("Day + " + i);
            EntityDisplay.displayEntity(qualityDegradationService.degradeProductByCategory(productMap, i));
        }
    }
}
