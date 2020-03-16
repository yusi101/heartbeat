package com.ruoyi.project.business.cartoon.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.cartoon.mapper.CartoonMapper;
import com.ruoyi.project.business.cartoon.domain.Cartoon;
import com.ruoyi.project.business.cartoon.service.ICartoonService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 漫画Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class CartoonServiceImpl implements ICartoonService 
{
    @Autowired
    private CartoonMapper cartoonMapper;

    /**
     * 查询漫画
     * 
     * @param cartoonId 漫画ID
     * @return 漫画
     */
    @Override
    public Cartoon selectCartoonById(Integer cartoonId)
    {
        return cartoonMapper.selectCartoonById(cartoonId);
    }

    /**
     * 查询漫画列表
     * 
     * @param cartoon 漫画
     * @return 漫画
     */
    @Override
    public List<Cartoon> selectCartoonList(Cartoon cartoon)
    {
        return cartoonMapper.selectCartoonList(cartoon);
    }

    /**
     * 新增漫画
     * 
     * @param cartoon 漫画
     * @return 结果
     */
    @Override
    public int insertCartoon(Cartoon cartoon)
    {
        cartoon.setCreateBy(ShiroUtils.getUserId().toString());
        cartoon.setCreateTime(DateUtils.getNowDate());
        return cartoonMapper.insertCartoon(cartoon);
    }

    /**
     * 修改漫画
     * 
     * @param cartoon 漫画
     * @return 结果
     */
    @Override
    public int updateCartoon(Cartoon cartoon)
    {
        cartoon.setUpdateBy(ShiroUtils.getUserId().toString());
        cartoon.setUpdateTime(DateUtils.getNowDate());
        return cartoonMapper.updateCartoon(cartoon);
    }

    /**
     * 删除漫画对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCartoonByIds(String ids)
    {
        return cartoonMapper.deleteCartoonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除漫画信息
     * 
     * @param cartoonId 漫画ID
     * @return 结果
     */
    @Override
    public int deleteCartoonById(Integer cartoonId)
    {
        return cartoonMapper.deleteCartoonById(cartoonId);
    }

    @Override
    public List<Cartoon> selectCartoonListByNewest() {
        return cartoonMapper.selectCartoonListByNewest();
    }
}
