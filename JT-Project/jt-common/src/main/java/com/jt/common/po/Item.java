package com.jt.common.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chao
 * @date 2019/1/25 - 15:35
 */
@Table(name="tb_item")
public class Item extends BasePojo {

	@Id		//定义注解, 主键自增
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;       // 标题元素
    private String sellPoint;   // 卖点信息
    private Long price;         // 价格，将价格扩大100倍保存，页面展示是缩小100倍
    private Integer num;        // 数量
    private String barcode;     // 二维码信息
    private String image;       // 图片信息 1.jpg,2.jpg...
    private Long cid;        	// 商品分类
    private Integer status;     // 1.正常，2.下架，3.删除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
