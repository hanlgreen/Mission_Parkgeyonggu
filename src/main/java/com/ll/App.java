package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private int lastQuotationId = 0;
    private List<Quotation> quotations = new ArrayList<>();
    private Scanner scanner;

    App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotations = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            }
        }
    }


    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        Quotation quotaion = new Quotation(id, content, authorName);
        quotations.add(quotaion);
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("-------------");

        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d /%s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    private void actionRemove(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String action = cmdBits[0];
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        int id = 0;

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paraName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (paraName.equals("id")) {
                id = Integer.parseInt(paramValue);
            }
        }
        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }
}
