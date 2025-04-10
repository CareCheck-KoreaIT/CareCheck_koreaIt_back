package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqAddOrderDto;
import com.korit.carecheckkoreait.dto.request.ReqSearchOrderDto;
import com.korit.carecheckkoreait.entity.Order;
import com.korit.carecheckkoreait.repository.OrderRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(ReqAddOrderDto reqAddOrderDto) {
        Order order = Order.builder()
                .orderCode(reqAddOrderDto.getOrderCode())
                .orderName(reqAddOrderDto.getOrderName())
                .orderScore(reqAddOrderDto.getOrderScore())
                .build();
        orderRepository.insert(order);
    }

    public List<Order> getAllOrders(ReqSearchOrderDto reqSearchOrderDto) throws Exception {
        return orderRepository.findAllByNameContaining(reqSearchOrderDto.getKeyword())
                .orElseThrow(() -> new NotFoundException("조회된 Order가 없습니다."));
    }

    public void updateScorePay(double scorePay) {
        orderRepository.updateScorePay(scorePay);
    }
    public void insertScorePay(double scorePay) {
        orderRepository.insertScorePay(scorePay);
    }

}
