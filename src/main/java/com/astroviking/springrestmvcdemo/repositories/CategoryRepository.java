package com.astroviking.springrestmvcdemo.repositories;

import com.astroviking.springrestmvcdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);
}
