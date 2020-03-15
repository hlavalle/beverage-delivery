package com.hlavalle.beveragedelivery.service;

import com.hlavalle.beveragedelivery.dto.RankingDto;
import com.hlavalle.beveragedelivery.exception.OrderNotFoundException;
import com.hlavalle.beveragedelivery.model.DeliveryOrder;
import com.hlavalle.beveragedelivery.model.Vehicle;
import com.hlavalle.beveragedelivery.repository.DeliveryOrderRepository;
import com.hlavalle.beveragedelivery.repository.VehicleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    private VehicleRepository vehicleRepository = Mockito.mock(VehicleRepository.class);
    private DeliveryOrderRepository deliveryOrderRepository = Mockito.mock(DeliveryOrderRepository.class);
    private OrderService orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(deliveryOrderRepository, vehicleRepository);
    }

    @Test(expected = OrderNotFoundException.class)
    public void shouldThrowOrderNotFoundException() {
        when(deliveryOrderRepository.findAll()).thenThrow(OrderNotFoundException.class);
        orderService.getOrderRanking(anyLong());
    }

    @Test
    public void shouldRegisterDeliveryOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder(1l,"A", "A", 10);
        when(deliveryOrderRepository.save(any(DeliveryOrder.class))).then(returnsFirstArg());
        DeliveryOrder savedDeliveryOrder = orderService.registerDeliveryOrder(deliveryOrder);
        assertNotNull(savedDeliveryOrder.getId());
    }

    @Test
    public void shouldReturnOrderRanking() {
        DeliveryOrder deliveryOrder = new DeliveryOrder(1l,"A", "A", 10);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1L, "model 1", "A", "A", 10));
        vehicles.add(new Vehicle(2L, "model 2", "C", "C", 30));
        vehicles.add(new Vehicle(3L, "model 3", "E", "E", 50));

        RankingDto rankingDto1 = new RankingDto(1L, "model 1", "A", 10, 100);
        RankingDto rankingDto2 = new RankingDto(2L, "model 2", "C", 30, 50);
        RankingDto rankingDto3 = new RankingDto(3L, "model 3", "E", 50, 12);

        when(deliveryOrderRepository.findById(anyLong())).thenReturn(Optional.of(deliveryOrder));
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<RankingDto> orderRankingList = orderService.getOrderRanking(anyLong());

        assertEquals(3, orderRankingList.size());
        assertThat(orderRankingList, containsInAnyOrder(rankingDto1, rankingDto2, rankingDto3));

    }

}
