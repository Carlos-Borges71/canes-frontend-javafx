package com.canes.model.pk;

import com.canes.model.dpo.PedidoDPO;
import com.canes.model.dpo.ProdutoDPO;

public class PedidoProdutoPK {

    private PedidoDPO pedido;
    private ProdutoDPO produto;

    public PedidoDPO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDPO pedido) {
        this.pedido = pedido;
    }

    public ProdutoDPO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDPO produto) {
        this.produto = produto;
    }

}
