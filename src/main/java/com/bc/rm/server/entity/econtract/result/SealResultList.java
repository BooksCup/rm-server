package com.bc.rm.server.entity.econtract.result;

import com.bc.rm.server.entity.econtract.EcontractSeal;

import java.util.List;

/**
 * 印章返回列表
 *
 * @author zhou
 */
public class SealResultList {
    private Integer total;
    private List<EcontractSeal> seals;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<EcontractSeal> getSeals() {
        return seals;
    }

    public void setSeals(List<EcontractSeal> seals) {
        this.seals = seals;
    }
}
