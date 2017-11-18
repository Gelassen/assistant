package com.coderbunker.assistant.currency.model;


import android.renderscript.Double2;
import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {

    @SerializedName("AUD")
    @Expose
    private Double aud;
    @SerializedName("BGN")
    @Expose
    private Double bgn;
    @SerializedName("BRL")
    @Expose
    private Double brl;
    @SerializedName("CAD")
    @Expose
    private Double cad;
    @SerializedName("CHF")
    @Expose
    private Double chf;
    @SerializedName("CNY")
    @Expose
    private Double cny;
    @SerializedName("CZK")
    @Expose
    private Double czk;
    @SerializedName("DKK")
    @Expose
    private Double dkk;
    @SerializedName("GBP")
    @Expose
    private Double gbp;
    @SerializedName("HKD")
    @Expose
    private Double hkd;
    @SerializedName("HRK")
    @Expose
    private Double hrk;
    @SerializedName("HUF")
    @Expose
    private Double huf;
    @SerializedName("IDR")
    @Expose
    private Double idr;
    @SerializedName("ILS")
    @Expose
    private Double ils;
    @SerializedName("INR")
    @Expose
    private Double inr;
    @SerializedName("JPY")
    @Expose
    private Double jpy;
    @SerializedName("KRW")
    @Expose
    private Double krw;
    @SerializedName("MXN")
    @Expose
    private Double mxn;
    @SerializedName("MYR")
    @Expose
    private Double myr;
    @SerializedName("NOK")
    @Expose
    private Double nok;
    @SerializedName("NZD")
    @Expose
    private Double nzd;
    @SerializedName("PHP")
    @Expose
    private Double php;
    @SerializedName("PLN")
    @Expose
    private Double pln;
    @SerializedName("RON")
    @Expose
    private Double ron;
    @SerializedName("RUB")
    @Expose
    private Double rub;
    @SerializedName("SEK")
    @Expose
    private Double sek;
    @SerializedName("SGD")
    @Expose
    private Double sgd;
    @SerializedName("THB")
    @Expose
    private Double thb;
    @SerializedName("TRY")
    @Expose
    private Double tryCurrency;
    @SerializedName("ZAR")
    @Expose
    private Double zar;
    @SerializedName("EUR")
    @Expose
    private Double eur;
    @SerializedName("USD")
    @Expose
    private Double usd;

    public Double getAud() {
        return aud;
    }

    public void setAud(Double aud) {
        this.aud = aud;
    }

    public Double getBgn() {
        return bgn;
    }

    public void setBgn(Double bgn) {
        this.bgn = bgn;
    }

    public Double getBrl() {
        return brl;
    }

    public void setBrl(Double brl) {
        this.brl = brl;
    }

    public Double getCad() {
        return cad;
    }

    public void setCad(Double cad) {
        this.cad = cad;
    }

    public Double getChf() {
        return chf;
    }

    public void setChf(Double chf) {
        this.chf = chf;
    }

    public Pair<String, Double> getCny() {
        return new Pair<>("CNY", cny);
    }

    public void setCny(Double cny) {
        this.cny = cny;
    }

    public Double getCzk() {
        return czk;
    }

    public void setCzk(Double czk) {
        this.czk = czk;
    }

    public Double getDkk() {
        return dkk;
    }

    public void setDkk(Double dkk) {
        this.dkk = dkk;
    }

    public Double getGbp() {
        return gbp;
    }

    public void setGbp(Double gbp) {
        this.gbp = gbp;
    }

    public Pair<String, Double> getHkd() {
        return new Pair<>("HKD", hkd);
    }

    public void setHkd(Double hkd) {
        this.hkd = hkd;
    }

    public Double getHrk() {
        return hrk;
    }

    public void setHrk(Double hrk) {
        this.hrk = hrk;
    }

    public Double getHuf() {
        return huf;
    }

    public void setHuf(Double huf) {
        this.huf = huf;
    }

    public Double getIdr() {
        return idr;
    }

    public void setIdr(Double idr) {
        this.idr = idr;
    }

    public Double getIls() {
        return ils;
    }

    public void setIls(Double ils) {
        this.ils = ils;
    }

    public Double getInr() {
        return inr;
    }

    public void setInr(Double inr) {
        this.inr = inr;
    }

    public Double getJpy() {
        return jpy;
    }

    public void setJpy(Double jpy) {
        this.jpy = jpy;
    }

    public Double getKrw() {
        return krw;
    }

    public void setKrw(Double krw) {
        this.krw = krw;
    }

    public Double getMxn() {
        return mxn;
    }

    public void setMxn(Double mxn) {
        this.mxn = mxn;
    }

    public Double getMyr() {
        return myr;
    }

    public void setMyr(Double myr) {
        this.myr = myr;
    }

    public Double getNok() {
        return nok;
    }

    public void setNok(Double nok) {
        this.nok = nok;
    }

    public Double getNzd() {
        return nzd;
    }

    public void setNzd(Double nzd) {
        this.nzd = nzd;
    }

    public Double getPhp() {
        return php;
    }

    public void setPhp(Double php) {
        this.php = php;
    }

    public Double getPln() {
        return pln;
    }

    public void setPln(Double pln) {
        this.pln = pln;
    }

    public Double getRon() {
        return ron;
    }

    public void setRon(Double ron) {
        this.ron = ron;
    }

    public Pair<String, Double> getRub() {
        return new Pair<>("RUB", rub);
    }

    public void setRub(Double rub) {
        this.rub = rub;
    }

    public Double getSek() {
        return sek;
    }

    public void setSek(Double sek) {
        this.sek = sek;
    }

    public Pair<String, Double> getSgd() {
        return new Pair<>("SGD", sgd);
    }

    public void setSgd(Double sgd) {
        this.sgd = sgd;
    }

    public Double getThb() {
        return thb;
    }

    public void setThb(Double thb) {
        this.thb = thb;
    }

    public Double getTryCurrency() {
        return tryCurrency;
    }

    public void setTryCurrency(Double tryCurrency) {
        this.tryCurrency = tryCurrency;
    }

    public Double getZar() {
        return zar;
    }

    public void setZar(Double zar) {
        this.zar = zar;
    }

    public Pair<String, Double> getEur() {
        return new Pair<>("EUR", eur);
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Pair<String, Double> getUsd() {
        return new Pair<>("USD", usd);
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }
}
