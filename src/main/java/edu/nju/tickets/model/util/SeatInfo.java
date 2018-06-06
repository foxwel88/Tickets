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

        public String getInfoString() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < infoList.size(); ++i) {
                if (i != 0) {
                    result.append(",");
                }
                result.append(String.valueOf(infoList.get(i)));
            }
            return result.toString();
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

    public SeatInfo(String nameString, String infoString) {
        districtList = new ArrayList<>();
        totalSeatNum = 0;

        String[] nameStringList = nameString.split("@");
        String[] infoStringList = infoString.split("@");

        for (int i = 0; i < nameStringList.length; ++i) {
            String name = nameStringList[i];
            String info = infoStringList[i];
            String[] tempList = info.split(",");

            District district = new District(name);
            int seatNum = 0;

            for (String s: tempList) {
                int num = Integer.valueOf(s);
                seatNum = seatNum + num;
                district.infoList.add(num);
            }
            district.seatNum = seatNum;

            districtList.add(district);
            totalSeatNum = totalSeatNum + seatNum;
        }
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

}
