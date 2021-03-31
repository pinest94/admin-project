package com.example.study.interfaces;

import com.example.study.network.Header;

public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> req); // todo request object 추가

    Header<Res> read(Long id); //

    Header<Res> update(Header<Req> req);

    Header<Res> delete(Long id);
}
