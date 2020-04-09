package com.astroviking.springrestmvcdemo.bootstrap;

import com.astroviking.springrestmvcdemo.domain.Category;
import com.astroviking.springrestmvcdemo.domain.Customer;
import com.astroviking.springrestmvcdemo.repositories.CategoryRepository;
import com.astroviking.springrestmvcdemo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

  CategoryRepository categoryRepository;
  CustomerRepository customerRepository;

  public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(String... args) {
    loadCategories();
    loadCustomers();
  }

  private void loadCustomers() {
    Customer customer1 = new Customer(1L, "John", "Brown");
    Customer customer2 = new Customer(2L, "Jenny", "Apple");
    Customer customer3 = new Customer(3L, "Ken", "Blue");
    Customer customer4 = new Customer(4L, "Mike", "Scott");
    Customer customer5 = new Customer(5L, "Sam", "Wise");

    customerRepository.save(customer1);
    customerRepository.save(customer2);
    customerRepository.save(customer3);
    customerRepository.save(customer4);
    customerRepository.save(customer5);

    System.out.println("Customers loaded = " + customerRepository.count());
  }

  private void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);

    System.out.println("Fruit Loaded = " + categoryRepository.count());
  }
}
