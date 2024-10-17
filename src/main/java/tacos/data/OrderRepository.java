package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.data.entity.TacoOrder;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

}
