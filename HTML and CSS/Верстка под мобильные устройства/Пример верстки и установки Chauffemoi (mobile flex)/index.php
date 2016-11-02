<?php
$base_path  = $_SERVER['DOCUMENT_ROOT']."/";
$base_url   = "http://".$_SERVER['HTTP_HOST']."/";
$base_landing   = $base_url."app/landings/1098f9/";
$inc_landing    = $base_path.'app/landings/includes/';

$v = 'pc';
if (isset($_SESSION['is_tablet']) && $_SESSION['is_tablet'] == 1){
    $v = 't';
}
if (isset($_SESSION['is_mobile']) && $_SESSION['is_mobile'] == 1 && $_SESSION['is_tablet'] != 1){
    $v = 'm';
}

?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title><?php if(isset($meta_title)) { echo $meta_title; }else{ echo $config['meta']['default']['title']; } ?></title>
        <link rel="shortcut icon" href="<?= BASEURL; ?>public/img/favicon.ico" type="image/x-icon" />
        <link rel="icon" href="<?= BASEURL; ?>public/img/favicon.ico" type="image/x-icon" />
        <meta name="description" content="<?php if(isset($meta_description)) { echo $meta_description; }else{ echo  $config['meta']['default']['description'] ; } ?>" />
        <meta name="keywords" content="<?php if(isset($meta_keywords)) { echo $meta_keywords; } else{ echo  $config['meta']['default']['keywords']; } ?>" />
        <?php
        if(isset($meta_add)) {
            echo $meta_add."\n";
        }
        if(defined('METAVERIF')) {
            echo METAVERIF;
        }
        ?>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<?= BASEURL; ?>public/css/bootstrap.css" media="screen">        
        <link rel="stylesheet" href="<?= $base_landing; ?>../css_landing.css" type="text/css" media="screen, projection" />
        <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="<?= $base_landing; ?>styles/reset.css" type="text/css" media="screen, projection" />
        <?php 
            if ($v != 'pc'){
        ?>
        <link rel="stylesheet" href="<?= $base_landing; ?>styles/<?php echo($v == 't' ? 'tablet' : 'mobile'); ?>.css" type="text/css" media="screen, projection" />     
        <?php
            }
        ?>
        
        <script type="text/javascript" src="<?= $base_landing; ?>js/jquery-1.6.1.js"></script>
        <script src="<?= BASEURL; ?>public/js/lib.js"></script>      
        <script type="text/javascript" src="<?= $base_landing; ?>../js_landing.js"></script>
        <script type="text/javascript" src="<?= $base_landing; ?>js/placeholder.js"></script>
        <?php 
            if ($v != 'pc'){
        ?>
        <script type="text/javascript" src="<?= $base_landing; ?>js/mobile.flex.js"></script>
        <?php
            }
        ?>
        <script type="text/javascript" src="<?= $base_landing; ?>js/radios.chboxes.js"></script>
        <script type="text/javascript" src="<?= BASEURL; ?>public/js/mailcheck.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#signinBtn').click(function(){
                    $('#signinBtn, #content-lev-1').hide();
                    $('#enter, #signonBtn, #enter-form-space').show();
                    initCss();
                });
                $('#signonBtn').click(function(){
                    $('#signonBtn, #enter, #signin3, #enter-form-space, #lost-pwd-form-space').hide();
                    $('#signinBtn, #content-lev-1').show();
                    initCss();
                });
                $('#lost_pwd').click(function(){
                    $('#enter, #enter-form-space').hide();
                    $('#signin3, #lost-pwd-form-space').show();
                    initCss();
                });
                $('#close-1').click(function(){
                    $('#signin3, #lost-pwd-form-space').hide();
                    $('#enter, #enter-form-space').show();
                    initCss();
                });
                
                $('#valider-part-1').click(function(){
                    var formOk = true; 
                    if (($('#mail').val() == '') || ($('#mail').val() == 'Mi email')) {
                        formOk = false;
                        $('#err_mail').show();
                    }
                    else {
                        var str = $('#mail').val();
                        formOk = false;
                        for(var i = 0; i < str.length; i++){
                            if (str[i] == '@' && str[i+2]){
                                formOk = true;
                            }
                        }
                        if (formOk == false) {
                            $('#err_mail').show();
                        }
                    } 
                    if (($('#field-login').val() == '') || ($('#field-login').val() == 'Mon pseudo')) {
                        formOk = false;
                        $('#err_login').show();
                    }
                    if ($('#accept_conditions').is(':checked') == false) {
                        formOk = false;
                        $('#err_cond').show();
                        $('#err_cond_square').show();                        
                    }
                    if (formOk == true) {
                        $('#part-1, #form-h1-1').hide();
                        $('#part-2, #form-h1-2').show();
                        initCss();
                    } 
                });
                $('#valider-part-2').click(function(){
                    var formOk = true; 
                    if ($('#passw').val().length < 1) {
                        formOk = false;
                        $('#err_pass').show();
                    }
                    if ($('select[name=day]').val() == '' || $('select[name=month]').val() == '' || $('select[name=year]').val() == '') {
                        formOk = false;
                        $('#err_date').show();
                    }
                    if (($('#ville').val() == '') || ($('#ville').val() == 'Ma ville')) {   
                        formOk = false;
                        $('#err_ville').show();
                    } 
                    if (formOk == true) {
                        $('#valider_submit').click(); 
                    } 
                });
                
                $(document).keypress(function(e){
                     e = e || window.event;
                     if(e.keyCode == 13){
                         return false;
                     }
                });
                
                $('#pass_auth, #login_auth').focus(function(){
                    $('#error1').hide(); 
                });
                $('#mail3').focus(function(){
                    $('#error2').hide();                    
                });
                $('#error1').click(function(){
                    $('#login_auth').focus();                    
                });
                $('#error2').click(function(){
                    $('#mail3').focus();                    
                });
                $('.error').click(function(){
                    $(this).siblings('input').focus();
                });
                $('#field-login, #err_login, #err_login2').click(function(){
                    $('#err_login, #err_login2').hide(); 
                });
                $('#passw, #err_pass, #err_pass2').click(function(){
                    $('#err_pass, #err_pass2').hide(); 
                });
                $('#mail, #err_mail, #err_mail2').click(function(){
                    $('#err_mail, #err_mail2').hide();
                });
                $('#ville, #err_ville, #err_ville2').click(function(){
                    $('#err_ville, #err_ville2').hide();                  
                });
                $('#date, #err_date, #err_date2').click(function(){
                    $('#err_date, #err_date2').hide();  
                });
                $('.checkbox1, #err_cond, #err_cond2').click(function(){
                    $('#err_cond, #err_cond2, #err_cond_square').hide();                    
                });
                
                $('input[placeholder]').placeholder();                
                
                var pholder;
                $('input').focus(function(){
                    pholder = $(this).attr('placeholder');
                    $(this).attr('placeholder','');
                    $('.error1, error2').hide();
                });
                $('input').blur(function(){
                    $(this).attr('placeholder',pholder);
                });
                
                $('.inp-bg p').click(function(){
                    $(this).siblings('input').focus();
                });
                
                var suggmail, usrmail;
                function check_mail() {
                    $("#mail").mailcheck({
                        suggested: function(element, suggestion) {	
                            $('#sugg_mail').html("Vous voulez dire <a href='#' onclick='return false'>" + suggestion.full + "</a>");
                            usrmail = $('#mail').val().split('@')[1];
                            suggmail = suggestion.full.split('@')[1];
                        },
                        empty: function(element) { usrmail = $('#mail').val().split('@')[1]; suggmail = usrmail;}
                    });                
                }
                $('#mail').keyup(function(){
                    check_mail();  
                    if (usrmail == suggmail){
                        $('#sugg_mail').hide();
                        $('.mal').css('margin-bottom','4%');
                    }
                    else {
                        $('#sugg_mail').show();
                        $('.mal').css('margin-bottom','8%');
                    }                    
                });
                
                $('#sugg_mail').click(function(){
                    var str = $(this).text().split(' ');
                    var correctMail = str[3];
                    $('#mail').val(correctMail).focus();
                    $(this).hide();
                    $('.mal').css('margin-bottom','4%');
                });
            });
        </script>
</head>

<body>
    
    <?php
        if ($v != 'pc'){
    ?>
    <header>
        <div id="h-center" data-height="<?php echo($v == 't' ? '11' : '16'); ?>%">
            <a href="#" onclick="return false" id="logo" class="pie" data-height="23%" data-absolute-v-center="50%">
                <img src="<?= $base_landing; ?>images/logo.png" alt="" />
            </a>
            <a href="#" onclick="return false" id="signinBtn" class="pie" data-absolute-v-center="<?php echo($v == 't' ? '35' : '38'); ?>%">Connexion</a>
            <a href="#" onclick="return false" id="signonBtn" class="pie" data-absolute-v-center="<?php echo($v == 't' ? '35' : '38'); ?>%">Inscription</a>
            <a href="#" onclick="return false" id="dm2" data-absolute-v-center="50%">Déjà membre?</a>
            <form id="enter" class="pie" method="post" name="" action="<?= $base_url; ?>index.php?mod=connect" data-height="<?php echo($v == 't' ? '67' : '87'); ?>%" data-absolute-h-center="50%">
                <input type="hidden" value="1" name="connecting">
                <a href="#" onclick="return false" id="dm">Déjà membre?</a>
                <div class="inp-bg type-1 pie login_place" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                    <input type="text" placeholder="Pseudo" name="login_auth" id="login_auth" tabindex="1" data-absolute-v-center="46%" data-absolute-h-center="50%" />                    
                </div>  
                <div class="inp-bg type-1 pie pass_place" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                    <input type="password"  placeholder="Mot de passe" name="pass_auth" id="pass_auth" tabindex="2" data-absolute-v-center="46%" data-absolute-h-center="50%" />   
                    <p id="error1">Login ou mot de passe incorrect</p>
                </div>
                <p class="chbox type-1">
                    <span class="input-wrap checked" data-height="100%">
                        <input type="checkbox" id="remember" name="remember_auth" value="7" checked="checked" />
                    </span>
                    Remember me
                </p>
                <a id="lost_pwd" href="#">Mot de passe oublié?</a>
                <input type="submit" id="enter-btn" class="pie" value="Je confirme" data-height="25%" data-absolute-h-center="50%" />  
            </form>
            <form action="<?= $base_url; ?>index.php?mod=landing" id="signin3" class="pie" method="post" name="signin3" data-height="<?php echo($v == 't' ? '50' : '60'); ?>%" data-absolute-h-center="50%">  
                <a href="#" onclick="return false" id="rvc">Rappel du mot de passe</a>
                <div class="inp-bg type-1 pie mail3_place" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                    <input type="text" placeholder="Votre login ou e-mail" name="mail3" id="mail3"  data-absolute-v-center="46%" data-absolute-h-center="50%" /> 
                </div>
                <input type="submit" id="get-email-btn" class="pie" value="Envoyer" data-height="25%" data-absolute-h-center="50%" />      
                <div id="close-1" class="pie" data-height="100%"></div>
            </form>
        </div>
    </header>
    
    <article>
        <div id="enter-form-space" data-height="<?php echo($v == 't' ? '56' : '71'); ?>%"></div>
        <div id="lost-pwd-form-space" data-height="<?php echo($v == 't' ? '42' : '50'); ?>%"></div>
        <div id="content-lev-1">
            <p id="h1"><span class="pie">Femme chaude</span><br/>en ligne pour flirter en direct!</p>
            <div id="for_signin_form" class="pie">
                <p id="form-h1-1"><span>Inscrivez-vous gratuitement</span><br/>Chauffemoi.com le rendez-vous des coquins pour une rencontre chaude.</p>
                <form action="<?= $base_url; ?>index.php?mod=landing" id="signin" class="pie" method="post" name="signin">
                    <div id="part-1">
                        <div class="inp-bg type-2 log pie" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                            <p id="err_login">Vous devez choisir un pseudo</p>
                            <input type="text" placeholder="Mon pseudo" name="login" id="field-login" class="pie" maxlength="20" autocomplete="on" data-absolute-v-center="46%" data-absolute-h-center="50%" />
                        </div>
                        <div class="inp-bg type-2 mal pie" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                            <p id="err_mail">Email invalide</p>
                            <p id="sugg_mail"></p>
                            <input type="text" placeholder="Mon email" name="mail" id="mail" autocomplete="on" data-absolute-v-center="46%" data-absolute-h-center="50%" />
                        </div>
                        <p id="form-txt-1">L'accès illimité à toutes les fonctionnalités du site requiert un abonnement payant.</p>
                        <p class="chbox type-2">
                            <span class="input-wrap checked" data-height="100%">
                                <input type="checkbox" id="accept_conditions" tabindex="16" name="agree" value="1" checked="checked" />
                            </span>
                            <span id="err_cond">Vous devez accepter les conditions générales<br/></span>J'accepte les <a href="#" onclick="return false">Conditions Générales d'Utilisation</a> ainsi que la <a href="#" onclick="return false">Politique de Confidentialité des Données</a> et j'accepte de recevoir un rappel de mes identifiants, des alertes et des  newsletters sur l'activité de Cafécoquin.
                        </p>
                        <div class="clear"></div>
                        <p class="valider pie" id="valider-part-1">Je confirme</p>
                    </div>
                    <div id="part-2">
                        <p id="form-h1-2">Complétez votre profil et rendez-vous sur le dial pour vos rencontres!</p>
                        <div class="inp-bg type-2 vil pie" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                            <p id="err_ville">Vous devez choisir une ville</p>                            
                            <input type="text" placeholder="Ma ville" id="ville" name="ville" autocomplete="on" data-absolute-v-center="46%" data-absolute-h-center="50%" />
                            <div id="props" style="display: none;" data-height="70%">
                            </div>
                        </div>
                        <div class="inp-bg type-2 pas pie" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                            <p id="err_pass">Vous devez choisir un mot de passe</p>
                            <input type="password" placeholder="Mot de passe" name="pass" id="passw" class="pie" maxlength="14" data-absolute-v-center="46%" data-absolute-h-center="50%" />
                        </div>
                        <div id="date" class="pie" data-height="<?php echo($v == 't' ? '9' : '12'); ?>%">
                            <p class="label1">Ma date de naissance</p>
                            <p id="err_date">Vous devez indiquer une date de naissance</p>
                            <div class="customSelectWrap" data-height="<?php echo($v == 't' ? '27' : '37'); ?>%">
                                <select name="day" id="day" tabindex="1" data-height="36%" data-absolute-v-center="50%">
                                    <option class="1" value="01">01</option>
                                    <option class="2" value="02">02</option>
                                    <option class="3" value="03">03</option>
                                    <option class="4" value="04">04</option>
                                    <option class="5" value="05">05</option>
                                    <option class="6" value="06">06</option>
                                    <option class="7" value="07">07</option>
                                    <option class="8" value="08">08</option>
                                    <option class="9" value="09">09</option>
                                    <option class="10" value="10">10</option>
                                    <option class="11" value="11">11</option>
                                    <option class="12" value="12">12</option> 
                                    <option class="13" value="13">13</option>
                                    <option class="14" value="14">14</option>
                                    <option class="15" value="15">15</option>
                                    <option class="16" value="16">16</option>
                                    <option class="17" value="17">17</option>
                                    <option class="18" value="18">18</option>
                                    <option class="19" value="19">19</option>
                                    <option class="20" value="20">20</option>
                                    <option class="21" value="21">21</option>
                                    <option class="22" value="22">22</option>
                                    <option class="23" value="23">23</option> 
                                    <option class="24" value="24">24</option>
                                    <option class="25" value="25">25</option>
                                    <option class="26" value="26">26</option>
                                    <option class="27" value="27">27</option>
                                    <option class="28" value="28">28</option>
                                    <option class="29" value="29">29</option>
                                    <option class="30" value="30">30</option>
                                    <option class="31" value="31">31</option>                 
                                    <option class="32" value="" selected="selected">Jour</option>
                                </select>
                            </div>
                            <div class="customSelectWrap border-lr-1" data-height="<?php echo($v == 't' ? '27' : '37'); ?>%">
                                <select name="month" id="month" tabindex="2" data-height="36%" data-absolute-v-center="50%">
                                    <option class="1" value="01">01</option>
                                    <option class="2" value="02">02</option>
                                    <option class="3" value="03">03</option>
                                    <option class="4" value="04">04</option>
                                    <option class="5" value="05">05</option>
                                    <option class="6" value="06">06</option>
                                    <option class="7" value="07">07</option>
                                    <option class="8" value="08">08</option>
                                    <option class="9" value="09">09</option>
                                    <option class="10" value="10">10</option>
                                    <option class="11" value="11">11</option>
                                    <option class="12" value="12">12</option>                                                  
                                    <option class="13" value="" selected="selected">Mois</option>
                                </select>
                            </div>
                            <div class="customSelectWrap" data-height="<?php echo($v == 't' ? '27' : '37'); ?>%">
                                <select name="year" id="year" tabindex="3" data-height="36%" data-absolute-v-center="50%">
                                    <option class="1" value="2001">2001</option>
                                    <option class="2" value="2002">2002</option>
                                    <option class="3" value="2003">2003</option>
                                    <option class="4" value="2004">2004</option>
                                    <option class="5" value="2005">2005</option>
                                    <option class="6" value="2006">2006</option>
                                    <option class="7" value="2007">2007</option> 
                                    <option class="8" value="" selected="selected">Année</option>
                                </select>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div id="radios">
                            <p class="label2">Je suis:</p>
                            <p class="radio type-1">
                                <span class="input-wrap checked" data-height="100%">
                                    <input type="radio" name="sex" id="sex3" value="3" checked="checked"/>
                                </span>
                                Un homme
                            </p>
                            <p class="radio type-1">
                                <span class="input-wrap no-checked" data-height="100%">
                                    <input type="radio" name="sex" id="sex2" value="2"/>
                                </span>
                                Une femme
                            </p>
                            <p class="radio type-1">
                                <span class="input-wrap no-checked" data-height="100%">
                                    <input type="radio" name="sex" id="sex1" value="1"/>
                                </span>
                                Un couple
                            </p>
                            <div class="clear"></div>
                        </div>
                        <div id="chboxes">
                            <p class="label2">Je recherche:</p>
                            <p class="chbox type-3">
                                <span class="input-wrap no-checked" data-height="100%">
                                    <input id="chch1" name="" value="1" type="checkbox" />
                                </span>
                                Un homme
                            </p>
                            <p class="chbox type-3">
                                <span class="input-wrap checked" data-height="100%">
                                    <input id="chch2" name="" value="2" type="checkbox" checked="checked" />
                                </span>
                                Une femme
                            </p>
                            <p class="chbox type-3">
                                <span class="input-wrap no-checked" data-height="100%">
                                    <input id="chch3" name="" value="3" type="checkbox" />
                                </span>
                                Un couple
                            </p>
                            <div class="clear"></div>
                        </div>
                        <p class="valider pie" id="valider-part-2">Accéder au site</p>
                        <input type="submit" id="valider_submit" value="" />
                    </div>
                </form>
            </div>
        </div> 
        
        <div id="content-lev-2">
            <div id="photos">
                <p id="photos-h1">Les derniers inscrits</p>
                <ul class="justify">
                    <li data-height="100%">
                        <a href="#">
                            <img src="<?= $base_landing; ?>images/user-photo.jpg" alt="" />
                        </a>
                    </li>
                    <li data-height="100%">
                        <a href="#"> 
                            <img src="<?= $base_landing; ?>images/user-photo.jpg" alt="" />
                        </a>
                    </li>
                    <li data-height="100%">
                        <a href="#">
                            <img src="<?= $base_landing; ?>images/user-photo.jpg" alt="" />
                        </a>
                    </li>
                </ul>
            </div>
        </div> 
        
        <div id="content-lev-3">
            <div id="icons">
                <p id="icons-h1">Qui sera le plus <span>CHAUD</span> ou la plus <span>CHAUDE</span> sur Chauffemoi?</p>
                <div id="album" class="point" data-height="<?php echo($v == 't' ? '51' : '22'); ?>%">
                    <div data-height="72%"></div>
                    <p>Albums photos</p>
                </div>
                <div id="votes" class="point" data-height="<?php echo($v == 't' ? '51' : '22'); ?>%">
                    <div data-height="92%"></div>
                    <p>Votes et classements</p>
                </div>
                <div id="tchat" class="point" data-height="<?php echo($v == 't' ? '51' : '22'); ?>%">
                    <div data-height="76%"></div>
                    <p>Tchat coquin direct</p>
                </div>
                <div class="clear"></div>
            </div>
        </div> 
    </article>
    <footer>
        <div id="f-center">
            <p id="links">
                <a href="#" onclick="return false">Conditions Générales d'Utilisation</a>
                <a href="#" onclick="return false">Informations légales</a>
                <a href="#" onclick="return false">Politique de confidentialité des données</a>
                <a href="#" onclick="return false">Recherche</a>
                <a href="#" onclick="return false">Rendez-vous Réels</a>
                <a href="#" onclick="return false">Albums photos</a>
                <a href="#" onclick="return false">Classements et votes</a>
            </p>
            <p id="footer-txt">Chauffemoi.com, Le site des aventures coquines discrètes sans lendemain ou d'une relation très chaude suivie avec une femme ou un homme très chaud. Rencontre chaude et flirt en ligne sur le chat. Vous cherchez une femme chaude pour une rencontre sensuelle ? Inscrivez-vous gratuitement en quelques clics, sans oublier de compléter votre profil et de rajouter au moins une photo. Recherche rapide ou vote photo vous trouverez bien une femme chaude pour un dial coquin et plus si affinités!</p>
            <p id="copyright">Site réservé aux adultes de +18 ans <br/>&copy; Copyright 2015 chauffemoi.com - site, texte et photos protégés, toute reproduction sera systématiquement poursuivie</p>
        </div>
    </footer>
    
    <?php
        }
        else {
            echo '<br/>This is device version !';
        }
    ?>
</body>
</html>