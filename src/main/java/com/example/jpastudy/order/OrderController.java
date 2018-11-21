package com.example.jpastudy.order;

import com.example.jpastudy.order.domain.OrderRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/view/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/list")
    public String list(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable, Map<String, Object> model) {
        model.put("orders", orderRepository.findAll(pageable));
        return "/order/list";
    }
}
