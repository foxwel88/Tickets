package edu.nju.tickets.model.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxwel on 2018/3/9.
 */
public class SeatInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<District> districtList;

    private int totalSeatNum;

    public class District implements Serializable{
        private static final long serialVersionUID = 1L;
        String name;
        int seatNum;
        List<Integer> infoList;
        District(String name) {
            this.name = name;
            infoList = new ArrayList<>();
            this.seatNum = 0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeatNum() {
            return seatNum;
        }

        public void setSeatNum(int seatNum) {
            this.seatNum = seatNum;
        }

        public List<Integer> getInfoList() {
            return infoList;
        }

        public void setInfoList(List<Integer> infoList) {
            this.infoList = infoList;
        }
    }

    public int getTotalSeatNum() {
        return totalSeatNum;
    }

    public void setTotalSeatNum(int totalSeatNum) {
        this.totalSeatNum = totalSeatNum;
    }

    public SeatInfo(List<String> districtNameList, List<List<Integer>> districtInfoList) {
        districtList = new ArrayList<>();
        totalSeatNum = 0;

        for (int i = 0; i < districtNameList.size(); ++i) {
            int seatNum = 0;

            District district = new District(districtNameList.get(i));
            List<Integer> oldInfoList = districtInfoList.get(i);

            for (Integer currentSeatNum: oldInfoList) {
                seatNum = seatNum + currentSeatNum;
                district.infoList.add(currentSeatNum);
            }
            district.seatNum = seatNum;

            districtList.add(district);
            totalSeatNum = totalSeatNum + seatNum;
        }
    }

    public String getSeatName(int seatId) {
        int sum = 0;
        int districtId = 0;
        int rowId = 0;
        int sitId = 0;

        for (District district: districtList) {
            sum = sum + district.seatNum;
            if (seatId < sum) {
                seatId = seatId - (sum - district.seatNum);
                districtId = districtList.indexOf(district);
                break;
            }
        }

        District district = districtList.get(districtId);
        sum = 0;
        for (int currentRowId = 0; rowId < district.infoList.size(); ++currentRowId) {
            sum = sum + district.infoList.get(currentRowId);
            if (seatId < sum) {
                rowId = currentRowId;
                sitId = seatId - (sum - district.infoList.get(currentRowId));
                break;
            }
        }

        String districtName = district.name;
        String rowName = (rowId + 1) + "排";
        String sitName = (sitId + 1) + "座";
        return districtName + " " + rowName + sitName;
    }

    public int getSeatId(int districtId, int rowId, int sitId) {
        int seatId = 0;
        for (int i = 0; i < districtId; ++i) {
            seatId = seatId + districtList.get(i).seatNum;
        }

        List<Integer> list = districtList.get(districtId).infoList;

        for (int i = 0; i < rowId; ++i) {
            seatId = seatId + list.get(i);
        }

        seatId = seatId + sitId;

        return seatId;
    }


    public String getDistrictName(int districtId) {
        return districtList.get(districtId).getName();
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("北一区");
        nameList.add("北二区");
        nameList.add("南一区");
        nameList.add("南二区");
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(10);
        list1.add(12);
        list1.add(12);
        list1.add(14);
        list1.add(14); //72
        List<Integer> list2 = new ArrayList<>();
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(10);
        list2.add(12); // 62
        List<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(6);
        list3.add(7);
        list3.add(8);
        list3.add(9);
        list3.add(10); //45
        List<Integer> list4 = new ArrayList<>();
        list4.add(10);
        list4.add(10);
        list4.add(12);
        list4.add(12);
        list4.add(14);
        list4.add(14); //72

        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        SeatInfo seatInfo = new SeatInfo(nameList, list);

        for (int i = 0; i < seatInfo.districtList.size(); ++i) {
            System.out.println(seatInfo.districtList.get(i).name + " " + seatInfo.districtList.get(i).seatNum);
        }

        System.out.println(seatInfo.getSeatName(40));

        System.out.println(seatInfo.getSeatId(0, 3, 8));



        System.out.println(seatInfo.getSeatName(72));

        System.out.println(seatInfo.getSeatId(1, 0, 0));
    }

}
