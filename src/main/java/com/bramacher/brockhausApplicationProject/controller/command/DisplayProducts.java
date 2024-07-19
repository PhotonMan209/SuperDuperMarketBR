package com.bramacher.brockhausApplicationProject.controller.command;

import com.bramacher.brockhausApplicationProject.service.database.ProductService;
import com.bramacher.brockhausApplicationProject.view.display.EntityDisplay;

public class DisplayProducts implements CommandInterface {
    private final ProductService productService;

    public DisplayProducts(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(String[] args) {
        EntityDisplay.displayEntity(productService.getAllProducts());
    }
}
