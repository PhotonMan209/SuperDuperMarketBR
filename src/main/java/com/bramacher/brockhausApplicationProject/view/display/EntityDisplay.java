package com.bramacher.brockhausApplicationProject.view.display;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Display of Generics and maximum modularity for displaying other types of entities for easier user navigation if this would be required.
public class EntityDisplay {

    public static void displayEntity(List<?> entityList) {
        if (entityList.isEmpty()) {
            System.out.println("No entities found.");
            return;
        }

        Object firstEntity = entityList.get(0);
        Class<?> entityClass = firstEntity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        printHeader(fields, entityList);
        for (Object entity : entityList) {
            printEntityRow(entity, fields, entityList);
        }
        printFooter(fields, entityList);
    }

    private static void printHeader(Field[] fields, List<?> entityList) {
        System.out.println("-" .repeat(calculateTotalWidth(fields, entityList)));
        for (Field field : fields) {
            if (shouldDisplayField(field)) {
                String columnName = getColumnName(field);
                System.out.printf("%-" + calculateColumnWidth(field, entityList) + "s ", columnName);
            }
        }
        System.out.println();
        System.out.println("-" .repeat(calculateTotalWidth(fields, entityList)));
    }

    private static void printEntityRow(Object entity, Field[] fields, List<?> entityList) {
        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            if (shouldDisplayField(field)) {
                try {
                    Object value = field.get(entity);
                    String formattedValue = formatValue(value);
                    System.out.printf("%-" + calculateColumnWidth(field, entityList) + "s ", formattedValue);
                } catch (IllegalAccessException e) {
                    System.err.println("Error accessing field: " + field.getName());
                }
            }
        }
        System.out.println();
    }

    private static void printFooter(Field[] fields, List<?> entityList) {
        System.out.println("-" .repeat(calculateTotalWidth(fields, entityList)));
    }

    // Calculate total width of the table
    private static int calculateTotalWidth(Field[] fields, List<?> entityList) {
        int totalWidth = 0;
        for (Field field : fields) {
            if (shouldDisplayField(field)) {
                totalWidth += calculateColumnWidth(field, entityList) + 1; // Add 1 for the space between columns
            }
        }
        return totalWidth;
    }

    // Calculate column width based on field name and values
    private static int calculateColumnWidth(Field field, List<?> entityList) {
        int width = getColumnName(field).length();
        try {
            for (Object entity : entityList) {
                Object value = field.get(entity);
                String formattedValue = formatValue(value);
                width = Math.max(width, formattedValue.length());
            }
        } catch (IllegalAccessException e) {
            // Handle exception
        }
        return width;
    }

    // Get column name from @Column annotation or field name
    private static String getColumnName(Field field) {
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null && !columnAnnotation.name().isEmpty()) {
            return columnAnnotation.name();
        }
        return field.getName();
    }

    // Format value based on its type
    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else if (value instanceof ProductTypeEntity) { // Example for ManyToOne relationship
            return ((ProductTypeEntity) value).getName();
        } else {
            return value.toString();
        }
    }

    // Determine whether to display the field based on annotations
    private static boolean shouldDisplayField(Field field) {
        // Exclude fields annotated with @ManyToOne or @OneToMany (relationships)
        return !field.isAnnotationPresent(ManyToOne.class) && !field.isAnnotationPresent(OneToMany.class);
    }
}
