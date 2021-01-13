package com.example.study.interfaces;

import com.example.study.network.Header;

public interface CrudInterface {

    Header create(); // todo request object 추가

    Header read(Long id); //

    Header update();

    Header delete(Long id);
}
