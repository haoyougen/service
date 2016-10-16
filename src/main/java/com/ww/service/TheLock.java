package com.ww.service;

public interface TheLock {
 public boolean lock(String key);
 public boolean unlock(String key);
}
