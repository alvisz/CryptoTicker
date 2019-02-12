package com.cryptoticker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class CryptoTicker {
    URL imgUrl = new URL("https://source.unsplash.com/user/imleedhali/1280x720");

    JFrame window = new JFrame("Crypto Ticker");
    JLabel contentPane =  new JLabel();
    JLabel price;
    JLabel volume = new JLabel();
    JLabel change1H;
    JLabel change24H;
    JLabel change7D;
    JLabel marketCap;
    JLabel author =  new JLabel();
    ImageIcon backgroundImage;
    ImageIcon CoinIcon;

    JComboBox<Crypto> currencyList = new JComboBox();
    ArrayList<Crypto> CryptoList;

    boolean IsChanging = true;
    Crypto pickedCrypto;
    int changeSec = 15;

    public CryptoTicker() throws Exception {
        window.setUndecorated(false);
        window.setSize(1280,720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CryptoList = new CryptoService().getCurrencyList();
        this.setLayout();
        addThingsToCombo();
        window.setVisible(true);
        window.setResizable(true);
        window.pack();

    }

    private void addThingsToCombo(){
        int x = currencyList.getSelectedIndex();
        currencyList.removeAllItems();
        for (Crypto m : CryptoList){
            currencyList.addItem(m);
        }
        if (x == -1){
            currencyList.setSelectedIndex(0);
        } else {
            currencyList.setSelectedIndex(x);
        }
    }

    private void setLayout() throws Exception{
        try {
            backgroundImage = new ImageIcon(ImageIO.read(imgUrl));
        } catch (IOException e){
            System.out.println("No connection.");
            System.exit(0);
        }
        contentPane.setIcon( backgroundImage );
        contentPane.setLayout( new BorderLayout() );
        window.setContentPane( contentPane );
        price = new JLabel("Loading...", JLabel.CENTER);
        price.setFont(new Font("Arial", Font.BOLD, 100));
        price.setForeground(Color.white);

        window.getContentPane().add(price);

        volume = new JLabel("Loading volume...");
        volume.setBounds(50,300,0,0);
        volume.setSize(volume.getPreferredSize());
        volume.setForeground(Color.white);
        volume.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(volume, BorderLayout.PAGE_END);

        change1H = new JLabel("Loading change1h...");
        change1H.setBounds(50,340,0,0);
        change1H.setSize(change1H.getPreferredSize());
        change1H.setForeground(Color.white);
        change1H.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(change1H, BorderLayout.PAGE_END);

        change24H = new JLabel("Loading change24h...");
        change24H.setBounds(50,380,0,0);
        change24H.setSize(change24H.getPreferredSize());
        change24H.setForeground(Color.white);
        change24H.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(change24H, BorderLayout.PAGE_END);

        change7D = new JLabel("Loading change7d...");
        change7D.setBounds(50,420,0,0);
        change7D.setSize(change7D.getPreferredSize());
        change7D.setForeground(Color.white);
        change7D.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(change7D, BorderLayout.PAGE_END);

        marketCap = new JLabel("Loading marketcap...");
        marketCap.setBounds(50,460,0,0);
        marketCap.setSize(marketCap.getPreferredSize());
        marketCap.setForeground(Color.white);
        marketCap.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(marketCap, BorderLayout.PAGE_END);

        author = new JLabel("By Alvis Zalamans");
        author.setBounds(20,20, 0 ,0);
        author.setSize(author.getPreferredSize());
        author.setForeground(Color.white);
        author.setVerticalAlignment(JLabel.BOTTOM);
        window.getContentPane().add(author, BorderLayout.PAGE_END);
        window.getContentPane().add(currencyList, BorderLayout.PAGE_END);
        window.setVisible(true);
        Object obj = currencyList.getSelectedItem();
        currencyList.addActionListener(e -> {
            Crypto item = (Crypto) currencyList.getSelectedItem();
                pickedCrypto = item;
                if (item != null){
                    setData(item);
                }
        });
        refreshData();
    }

    public void setData(Crypto item){
        try {
            CoinIcon = new ImageIcon(ImageIO.read(new URL(item.getImgURL())));
            price.setText("$ "+Double.toString(roundPrice(item.getPrice())));
            price.setIcon(CoinIcon);
            volume.setText("Volume: "+DecimalFormat.getIntegerInstance().format(item.getVolume())+"$");
            volume.setFont(new Font("Arial", Font.BOLD, 20));
            volume.setSize(volume.getPreferredSize());

            change1H.setText("Change 1H: "+item.getChange1H()+"%");
            if (item.getChange1H()<0){
                change1H.setForeground(Color.RED);
            } else {
                change1H.setForeground(Color.GREEN);
            }
            change1H.setFont(new Font("Arial", Font.BOLD, 20));
            change1H.setSize(change1H.getPreferredSize());

            change24H.setText("Change 24H: "+item.getChange24H()+"%");
            if (item.getChange24H()<0){
                change24H.setForeground(Color.RED);
            } else {
                change24H.setForeground(Color.GREEN);
            }
            change24H.setFont(new Font("Arial", Font.BOLD, 20));
            change24H.setSize(change24H.getPreferredSize());

            change7D.setText("Change 7D: "+item.getChange7D()+"%");
            if (item.getChange7D()<0){
                change7D.setForeground(Color.RED);
            } else {
                change7D.setForeground(Color.GREEN);
            }
            change7D.setFont(new Font("Arial", Font.BOLD, 20));
            change7D.setSize(change7D.getPreferredSize());

            marketCap.setText("Market Cap: "+DecimalFormat.getIntegerInstance().format(item.getMarketCap())+"$");
            marketCap.setForeground(Color.white);
            marketCap.setFont(new Font("Arial", Font.BOLD, 20));
            marketCap.setSize(marketCap.getPreferredSize());


            CoinIcon = new ImageIcon(ImageIO.read(new URL(item.getImgURL())));
            price.setIcon(CoinIcon);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void refreshData() throws Exception{
        while (IsChanging){
            setLoading();
            backgroundImage = new ImageIcon(ImageIO.read(imgUrl));
            contentPane.setIcon( backgroundImage );
            CryptoList = new CryptoService().getCurrencyList();
            addThingsToCombo();
            Crypto item = (Crypto) currencyList.getSelectedItem();
            setData(item);
            new LogWriter(CryptoList);
            Thread.sleep(changeSec * 1000);
        }
    }

    private double roundPrice(Double price){
        double second = price;
        second = second *100;
        second = Math.round(second);
        second = second/100;
        return second;

    }

    private void setLoading(){
        price.setText("Loading...");
        volume.setText("Loading...");
        change1H.setText("Loading...");
        change1H.setForeground(Color.WHITE);
        change24H.setText("Loading...");
        change24H.setForeground(Color.WHITE);
        change7D.setText("Loading...");
        change7D.setForeground(Color.WHITE);
        marketCap.setText("Loading...");
    }
}
