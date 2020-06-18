package com.example.mobileapplicationsproject.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Question implements Serializable {
    private int x;
    private int y;
    private int operationCode;
    private String resultString = null;

    public Question(int x, int y, int operationCode) {
        this.x = x;
        if(operationCode==3){
            if(y==0){
                y++;
            }
        }
        this.y = y;
        this.operationCode = operationCode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String calculateAndRound() {
        x = this.x;
        y = this.y;
        operationCode = this.operationCode;

        switch (operationCode) {
            case 0:
                resultString = String.valueOf(x + y);
                break;
            case 1:
                resultString = String.valueOf(x - y);
                break;
            case 2:
                resultString = String.valueOf(x * y);
                break;
            case 3:
                BigDecimal bx = new BigDecimal(x);
                BigDecimal by = new BigDecimal(y);
                BigDecimal bResult = bx.divide(by, 2, RoundingMode.HALF_UP);
                resultString = bResult.stripTrailingZeros().toPlainString();
        }
        return resultString;
    }
}
