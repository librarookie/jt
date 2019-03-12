package com.jt.cart.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:35
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;


    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        return cartMapper.selectList(
                new QueryWrapper<Cart>().eq("user_id", userId));
    }

    @Override
    @Transactional
    public void updateCartNum(Cart cart) {
        Cart cartTemp = new Cart();
        cartTemp.setNum(cart.getNum());
        cartTemp.setCreated(new Date());
        cartMapper.update(cartTemp, new UpdateWrapper<Cart>()
                       .eq("user_id", cart.getUserId())
                       .eq("item_id", cart.getItemId()));
    }

    /**
     * 新增业务逻辑 item_id, user_id
     * 如果根据item_id 和 user_id 查询时
     *      数据库中有记录
     *          则只做数据的更新操作
     *      数据库中没有记录
     *          则执行数据的新增操作
     * @param cart
     */
    @Override
    @Transactional
    public void saveCart(Cart cart) {
        Cart cartDB =  cartMapper.selectOne(new QueryWrapper<Cart>()
                .eq("item_id", cart.getItemId())
                .eq("user_id", cart.getUserId()));
        if (cartDB == null) {
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        }else {
            int num = cart.getNum() + cartDB.getNum();
            // 只根据主键更新数据, 并且全部字段更新
            Cart cartTemp = new Cart();
            cartTemp.setNum(num);
            cartMapper.update(cartTemp, new UpdateWrapper<Cart>()
                    .eq("item_id", cart.getItemId())
                    .eq("user_id", cart.getUserId()));
        }
    }
}
