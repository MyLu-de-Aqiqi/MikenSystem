package com.miken.sys.login.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@AllArgsConstructor
public class BaseMapperController<T, R extends BaseMapper<T>, S extends IService<T>> {
	protected S service;
	protected R repository;

	@PostMapping
	@ApiOperation("新增")
	public T add(@RequestBody T t) {
		try {
			addBefore(t);
			Method setDeleted = t.getClass().getMethod("setDeleted");
			if (setDeleted != null) {
				setDeleted.invoke(t, false);
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		service.save(t);
		addAfter(t);
		return t;
	}

	protected void addBefore(T t) {
	}

	protected void addAfter(T t) {
	}

	protected void updateBefore(T t) {
	}

	protected void updateAfter(T t) {
	}

	protected void getAfter(T t) {
	}


	@PutMapping
	@ApiOperation("变更")
	public T update(@RequestBody T t) {
		updateBefore(t);
		service.updateById(t);
		updateAfter(t);
		return t;
	}

	@DeleteMapping("/{id}")
	@ApiOperation("删除")
	public void delete(@PathVariable(name = "id") Long id) {
		service.removeById(id);
	}

	@GetMapping("/{id}")
	@ApiOperation("单个查询")
	public T getOne(@PathVariable Long id) {
		T t = service.getById(id);
		getAfter(t);
		return t;
	}
}
