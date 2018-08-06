package mx.org.bamx.jsh.project.Fragments;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.Utils.Constants;

public class tabsFragment extends Fragment   {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public tabsFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tabs, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerContainer);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Utils.currentTab = tab.getText().toString();
                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
                Intent i = new Intent("TAG_REFRESH");
                lbm.sendBroadcast(i);

                tabLayout.getRootView().clearFocus();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (tabLayout!=null)
            if (tabLayout.getTabCount() >2)
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            else
                tabLayout.setTabMode(TabLayout.MODE_FIXED);


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        tabsFragment.ViewPagerAdapter adapter = new tabsFragment.ViewPagerAdapter(getChildFragmentManager());

        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Contactos:

                adapter.addFragment(new ContactsFragment(), Constants.HEADER_BANCOS_DE_ALIMENTOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_COMUNITARIOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_DE_ACOPIO);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_BENEFACTORES);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_PROVEDOREES);

                break;

            case Mapa:

                adapter.addFragment(new ContactsFragment(), Constants.HEADER_BANCOS_DE_ALIMENTOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_COMUNITARIOS);
                adapter.addFragment(new ContactsFragment(), Constants.HEADER_CENTROS_DE_ACOPIO);

                break;
            case Acopio:
                switch (Utils.opcionActualAcopio)
                {
                    case EntradasYSalidas:
                        adapter.addFragment(new ContactsFragment(), Constants.HEADER_ENTRADAS);
                        adapter.addFragment(new ContactsFragment(), Constants.HEADER_SALIDAS);
                        break;
                    case Donativos:

                        adapter.addFragment(new DetalleDonativoProductoFragment(), "PRODUCTO");
                        adapter.addFragment(new DetalleDonativoAcopioFragment(), "ACOPIO");
                        adapter.addFragment(new DetalleDonativoEspecificacionesFragment(), "ESPECIFICACIONES");
                        adapter.addFragment(new DetalleDonativoProcuradorFragment(), "PROCURADOR");

                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
